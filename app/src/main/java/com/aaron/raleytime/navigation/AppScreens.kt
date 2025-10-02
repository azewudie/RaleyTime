package com.aaron.raleytime.navigation

sealed class AppScreens(
    val route:String
) {
    data object AppRaleyTime: AppScreens(AppScreenRout.RALEY_TIME)
    data object AppStt: AppScreens(AppScreenRout.STT)
    data object AppTts: AppScreens(AppScreenRout.TTS)
}