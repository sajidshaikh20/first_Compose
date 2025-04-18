@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.firstcompose

import Quote
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstcompose.api.TweetsyApi
import com.example.firstcompose.datamanager.DataManager
import com.example.firstcompose.routes.RoutesConstant
import com.example.firstcompose.screen.QuateDetails
import com.example.firstcompose.screen.QuateListScreen
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.crashlytics.crashlytics
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tweetsyApi: TweetsyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)


        GlobalScope.launch {
            var response = tweetsyApi.getCategories()
            Log.d("MainActivity", "onCreate: ${response.body().toString()}")
        }
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            DataManager.loadAssetsFromFile(applicationContext)
        }
        val firebaseAnalytics = Firebase.analytics
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null)
        Log.d("BuildConfig", BuildConfig.BUILD_TYPE)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = RoutesConstant.quoteListScreen,
                builder = {
                    composable(RoutesConstant.quoteListScreen) {
                        App(navController)
                    }
                    composable(RoutesConstant.quoteDetails + "/{quote}") {
                        val quoteJson = it.arguments?.getString("quote")
                        val quote = Gson().fromJson(quoteJson, Quote::class.java)
                        QuateDetails(quote)
                    }

                })
        }
    }
}


@Composable
fun AnalyticsButton() {
    val analytics = remember { Firebase.analytics }
    Button(
        modifier = Modifier.padding(50.dp),
        onClick = {
            val bundle = Bundle().apply {
                putString("button_name", "analytics_button")
                putLong("timestamp", System.currentTimeMillis())
            }
            analytics.logEvent("compose_button_click", bundle)
        }
    ) {
        Text("Log Analytics Event")
    }
}


///Dummy example of side effect

@Preview
@Composable
fun ListComposable() {
    val category = remember { mutableStateOf(emptyList<String>()) }
    LaunchedEffect(key1 = Unit) {
        category.value = fetchCategory();
    }
    LazyColumn {
        items(category.value) { it ->
            Text(text = it);
        }
    }
}

fun fetchCategory(): List<String> {
    return listOf("one", "two", "three")
}

@Composable
fun CrashTestComposable() {
    Button(
        modifier = Modifier.padding(50.dp),
        onClick = {
            Firebase.crashlytics.log("Crash from Compose")
            Firebase.crashlytics.setCustomKey("compose_crash", "true")
            throw RuntimeException("Crash from Jetpack Compose!")
        }) {
        Text("Crash from Compose")
    }
}


@Composable
fun App(navController: NavHostController) {
    if (DataManager.isDataLoaded.value) {
        if (DataManager.currentPage.value == Pages.LISTING) {
            Scaffold(modifier = Modifier.fillMaxSize())
            { innerPadding ->
                QuateListScreen(
                    modifier = Modifier.padding(innerPadding),
                    data = DataManager.data,
                    onClick = { quote ->
                        val quoteJson = Uri.encode(Gson().toJson(quote)) // Serialize and encode
                        navController.navigate(RoutesConstant.quoteDetails + "/$quoteJson")
                    }
                )
            }
        } else {
            DataManager.currentQuote?.let { QuateDetails(quote = it) }
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Loading")
        }
    }
}


@Composable
fun RecompositionFunction() {
    val value = rememberSaveable { mutableIntStateOf(0) }
    Log.d("Tagged", "Logged during initial composition ")
    Button(
        onClick = {
            value.intValue = (1..1000).random()
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Log.d("Tagged", "Logged during both Composition and recomposition")
        Text(text = value.intValue.toString())
    }
}

@Composable
fun MessageCard(value: Int) {
    Card {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = "Checkmark",
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Message sent successfully! Message ID:-$value ",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

enum class Pages {
    LISTING,
    DETAILS
}


