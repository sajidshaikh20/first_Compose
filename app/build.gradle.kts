import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.google.firebase.crashlytics)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)


}

android {
    namespace = "com.app.okacleaner"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.app.okacleaner"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            applicationIdSuffix = ".debug"
        }
    }

    flavorDimensions += "env"
    productFlavors {
        create("dev") {
            dimension = "env"
            applicationIdSuffix = ".dev"




            val appPropertiesFile = rootProject.file("app/src/main/dev/app.properties")
            val appstoreProperties = Properties()
            appstoreProperties.load(FileInputStream(appPropertiesFile))


            buildConfigField(
                "String",
                "BASE_URL",
                appstoreProperties["BASE_URL"] as String
            )
            buildConfigField("String", "API_KEY", appstoreProperties["API_KEY"] as String)
            buildConfigField("String", "WEB_CLIENT_ID", appstoreProperties["WEB_CLIENT_ID"] as String)
            buildConfigField("Boolean", "ENABLE_CRASHLYTICS", appstoreProperties["android.enableCrashlytics"].toString())
            manifestPlaceholders["ENABLE_CRASHLYTICS"] = appstoreProperties["android.enableCrashlytics"].toString()
            buildConfigField("String", "FACEBOOK_APP_ID", appstoreProperties["FACEBOOK_APP_ID"].toString())
            manifestPlaceholders["FACEBOOK_APP_ID"] = appstoreProperties["FACEBOOK_APP_ID"].toString()
            buildConfigField("String", "FACEBOOK_CLIENT_TOKEN", appstoreProperties["FACEBOOK_CLIENT_TOKEN"].toString())
            manifestPlaceholders["FACEBOOK_CLIENT_TOKEN"] = appstoreProperties["FACEBOOK_CLIENT_TOKEN"].toString()
            buildConfigField("String", "SOCKET_URL_NEW", appstoreProperties["SOCKET_URL_NEW"].toString())
        }

        create("stage") {
            dimension = "env"
            applicationIdSuffix = ".stage"


            val appPropertiesFile = rootProject.file("app/src/main/stage/app.properties")
            val appstoreProperties = Properties()
            appstoreProperties.load(FileInputStream(appPropertiesFile))

            buildConfigField(
                "String",
                "BASE_URL",
                appstoreProperties["BASE_URL"] as String
            )

            buildConfigField("String", "API_KEY", appstoreProperties["API_KEY"] as String)
            buildConfigField("String", "WEB_CLIENT_ID", appstoreProperties["WEB_CLIENT_ID"] as String)
            buildConfigField("Boolean", "ENABLE_CRASHLYTICS", appstoreProperties["android.enableCrashlytics"].toString())
            manifestPlaceholders["ENABLE_CRASHLYTICS"] = appstoreProperties["android.enableCrashlytics"].toString()
            buildConfigField("String", "FACEBOOK_APP_ID", appstoreProperties["FACEBOOK_APP_ID"].toString())
            manifestPlaceholders["FACEBOOK_APP_ID"] = appstoreProperties["FACEBOOK_APP_ID"].toString()
            buildConfigField("String", "FACEBOOK_CLIENT_TOKEN", appstoreProperties["FACEBOOK_CLIENT_TOKEN"].toString())
            manifestPlaceholders["FACEBOOK_CLIENT_TOKEN"] = appstoreProperties["FACEBOOK_CLIENT_TOKEN"].toString()
            buildConfigField("String", "SOCKET_URL_NEW", appstoreProperties["SOCKET_URL_NEW"].toString())
        }

        create("prod") {
            dimension = "env"
            

            val appPropertiesFile = rootProject.file("app/src/main/release/app.properties")

            val appstoreProperties = Properties()
            appstoreProperties.load(FileInputStream(appPropertiesFile))

            buildConfigField(
                "String",
                "BASE_URL",
                appstoreProperties["BASE_URL"] as String
            )
            buildConfigField("String", "API_KEY", appstoreProperties["API_KEY"] as String)
            buildConfigField("String", "WEB_CLIENT_ID", appstoreProperties["WEB_CLIENT_ID"] as String)
            buildConfigField("Boolean", "ENABLE_CRASHLYTICS", appstoreProperties["android.enableCrashlytics"].toString())
            manifestPlaceholders["ENABLE_CRASHLYTICS"] = appstoreProperties["android.enableCrashlytics"].toString()
            buildConfigField("String", "FACEBOOK_APP_ID", appstoreProperties["FACEBOOK_APP_ID"].toString())
            manifestPlaceholders["FACEBOOK_APP_ID"] = appstoreProperties["FACEBOOK_APP_ID"].toString()
            buildConfigField("String", "FACEBOOK_CLIENT_TOKEN", appstoreProperties["FACEBOOK_CLIENT_TOKEN"].toString())
            manifestPlaceholders["FACEBOOK_CLIENT_TOKEN"] = appstoreProperties["FACEBOOK_CLIENT_TOKEN"].toString()
            buildConfigField("String", "SOCKET_URL_NEW", appstoreProperties["SOCKET_URL_NEW"].toString())
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.material)
    implementation(libs.gson)


    //navigation hilt
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.fragment)

    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    //View model
    implementation(libs.androidx.lifecycle.viewmodel)

     // View model utilits for compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)


    //Couroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)


    //retrofit For api calling
    implementation(libs.retrofit)


    implementation(libs.converter.gson)


    implementation(libs.logging.interceptor)




}