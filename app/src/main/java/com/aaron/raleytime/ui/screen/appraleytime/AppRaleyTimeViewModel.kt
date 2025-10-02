package com.aaron.raleytime.ui.screen.appraleytime

import androidx.lifecycle.viewModelScope
import com.aaron.raleytime.framework.baseviewmodel.BaseViewModel
import com.aaron.raleytime.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class AppRaleyTimeViewModel @Inject constructor(dataRepository: DataRepository) :
    BaseViewModel(dataRepository) {
    private val userStatus = runBlocking {
        dataStoreHelper.getFirstTimeUser()

    }
    private val _screenSate = MutableStateFlow(getInitialData())
    val screenState = _screenSate.asStateFlow()


    private fun getInitialData(): AppRaleyTimeScreenUIStates {
        return AppRaleyTimeScreenUIStates(
            appRaleyTimeScreenHeaderTitle = "Talk Type Transform.",
            appRaleyTimeScreenBodyText = "Seamlessly speak and see your words come to life or let your text do the talking. Whether you’re capturing ideas, crafting messages, or giving your voice a break, we've got both sides of the conversation covered.\n\nOne app. Two powerful tools. Infinite possibilities.",
            showAppDialogSummery = userStatus,
            appDialogSummeryBodyText ="Relay Time is a sleek, multi-screen Android app built with Jetpack Compose and a clean MVVM architecture, seamlessly integrating Google’s Speech-to-Text (STT) and Text-to-Speech (TTS) APIs to deliver instant, intuitive voice and text interaction. Featuring a smart onboarding home screen, a real-time speech transcription STT screen, and a natural-sounding TTS screen, the app offers a fully responsive UI inspired by Google Reply and validated on real Pixel devices. It incorporates modern best practices including scalable architecture, first-run dialog persistence via DataStore, smooth runtime permission handling, and built-in accessibility—making Relay Time a thoughtfully crafted, fast, and inclusive communication tool.\n\n A Jetpack Compose App by Aaron Zewudie",
            appDialogSummeryTitle = "Developed by Aaron Zewudie",
            appDialogSummeryButtonLabel = "Ok"
        )

    }

    fun onAppRaleyTimeScreenEvents(event: AppRaleyTimeScreenUIEvents) {
        when (event) {
            is AppRaleyTimeScreenUIEvents.OnClickOk -> {
                viewModelScope.launch {
                    dataStoreHelper.updateFirstTimeUser(true)
                }

            }
        }
    }
}