import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.okacleaner.MessageCard
import com.app.okacleaner.R
import com.app.okacleaner.RecompositionFunction
import com.app.okacleaner.ui.theme.ui.theme.MyNotificationUI

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyScreen() {
    /*val historyList = listOf(
        Triple("Recharge", "10/10/2023", "Successfully recharged"),
        Triple("Top-Up", "12/10/2023", "Balance added"),
        Triple("Data", "15/10/2023", "1.5 GB Activated")
    )*/
    val count : MutableState<Int> = rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Recharge History",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            Log.d("Navigate","pop up")
                        }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, "backIcon")}
                },
                actions = {

                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            item {
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        MyNotificationUI(
                            count.value
                        ) {
                            count.value++
                        }
                    }
                }
                MessageCard(count.value)

                RecompositionFunction()
            }
            items(30) { index ->
                RechargeHistoryCard(
                    img = R.drawable.ic_x,
                    title = "Recharge",
                    date = "12/10/2023",
                    desc = "Successfully recharged",
                    index = index,
                    modifier = Modifier
                )
            }
        }
    }
}