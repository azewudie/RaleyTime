plugins {
    alias(libs.plugins.androidApplication)
    kotlin("android")
    alias(libs.plugins.ktlint)
    alias(libs.plugins.daggerHiltAndroid)
    alias(libs.plugins.googleKsp)
    alias(libs.plugins.composeCompiler)
    kotlin("kapt")
}

android {
    namespace = "com.aaron.raleytime"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.aaron.raleytime"
        minSdk = 35
        targetSdk = 36
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.9.23"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // hilt dependencies
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.core.i18n)
    implementation(libs.androidx.compose.material3)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
// compose dependencies
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    ksp(libs.compose.destinations)
    implementation(libs.compose.destinations.core)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.compose.material3.window.size)
    implementation(libs.androidx.activity.compose)

    // data store
    implementation(libs.androidx.datastore.preferences)

// retrofit dependencies
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
// android core dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
// json dependency
    implementation(libs.jsoup)
// system ui controller dependency
    implementation(libs.accompanist.systemuicontroller)
// coil dependency
    implementation(libs.coil.compose)
    // material icons
// test dependencies
    testImplementation(libs.junit)
    testImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.junit.ext)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.assertions.truth)
    testImplementation(libs.mockk)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

}