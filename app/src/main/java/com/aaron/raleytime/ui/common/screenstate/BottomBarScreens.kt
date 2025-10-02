package com.aaron.raleytime.ui.common.screenstate

import com.aaron.raleytime.navigation.AppScreens


sealed class BottomBarScreens(
    val route:ArrayList<String>,
    val sectionName:String,
    val enable:Boolean = false,
) {
    data object RaleyTime: BottomBarScreens(
        route = arrayListOf(AppScreens.AppRaleyTime.route),
        sectionName = "Raley Time",
        enable = true
    )
    data object Stt: BottomBarScreens(
        route = arrayListOf(AppScreens.AppStt.route),
        sectionName = "STT",
        enable = true
    )
    data object Tts: BottomBarScreens(
        route = arrayListOf(AppScreens.AppTts.route),
        sectionName = "TTS",
        enable = true
    )
}