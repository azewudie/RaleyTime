package com.aaron.raleytime.ui.screen.apptts

import com.aaron.raleytime.data.local.speechtotexttextospeech.TextToSpeechState
import com.aaron.raleytime.utlits.constant.AppConstants

data class AppTtsScreenUIStates(
    val appTtsScreenHeaderTitle: String = AppConstants.EMPTY_STRING,
    val appTtsState: TextToSpeechState = TextToSpeechState(),
    val appTtsInputText: String = AppConstants.EMPTY_STRING
)