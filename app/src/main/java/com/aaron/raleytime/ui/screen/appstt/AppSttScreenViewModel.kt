package com.aaron.raleytime.ui.screen.appstt

import androidx.lifecycle.viewModelScope
import com.aaron.raleytime.data.service.SpeechRecognitionRepository
import com.aaron.raleytime.framework.baseviewmodel.BaseViewModel
import com.aaron.raleytime.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppSttScreenViewModel @Inject constructor(
    dataRepository: DataRepository,
    private val speechRecognitionRepository: SpeechRecognitionRepository
) :
    BaseViewModel(dataRepository) {
    private val _screenSate = MutableStateFlow(getInitialData())
    val screenState = _screenSate.asStateFlow()

    init {
        observeSpeechState()
    }

    private fun getInitialData(): AppSttScreenUIStates {
        return AppSttScreenUIStates(
            appSttScreenHeaderTitle = "Turn Speech into Text.",
        )

    }

    private fun observeSpeechState() {
        viewModelScope.launch {
            speechRecognitionRepository
                .observeSpeechState()
                .collect { speechState ->
                    _screenSate.update { currentState ->
                        currentState.copy(speechText = speechState)

                    }
                }
        }
    }

    fun onAppSttScreenEvents(event: AppSttScreenUIEvents) {
        when (event) {
            is AppSttScreenUIEvents.StartListening -> {
                startListening(event.language)
            }

            is AppSttScreenUIEvents.StopListening -> {
                stopListening()
            }

            AppSttScreenUIEvents.OnCopyClicked -> {
                _screenSate.update { currentState ->
                    currentState.copy(isCopyClicked = true)
                }
            }

            AppSttScreenUIEvents.OnUpDateToastMessageStatus -> {
                viewModelScope.launch {
                    delay(2000)
                    _screenSate.update { currentState ->
                        currentState.copy(isCopyClicked = false)
                    }
                }
            }
        }


    }

    private fun startListening(language: String) {
        speechRecognitionRepository.startListening(language)
    }

    private fun stopListening() {
        speechRecognitionRepository.stopListening()
    }

    override fun onCleared() {
        super.onCleared()
        speechRecognitionRepository.cleanup()
    }
}