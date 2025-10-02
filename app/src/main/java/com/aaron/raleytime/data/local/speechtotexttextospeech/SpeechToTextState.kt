package com.aaron.raleytime.data.local.speechtotexttextospeech

import com.aaron.raleytime.utlits.constant.AppConstants

data class SpeechToTextState(
    val spokenText:String = AppConstants.EMPTY_STRING,
    val isSpeaking:Boolean = false,
    val error:String = AppConstants.EMPTY_STRING,
    )