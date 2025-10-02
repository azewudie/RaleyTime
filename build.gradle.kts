// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetBrainsKotlin) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.daggerHiltAndroid) apply false
    alias(libs.plugins.googleKsp) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}