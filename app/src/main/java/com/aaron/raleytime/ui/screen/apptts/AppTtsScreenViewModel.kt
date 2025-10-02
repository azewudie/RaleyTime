package com.aaron.raleytime.ui.screen.apptts

import androidx.lifecycle.viewModelScope
import com.aaron.raleytime.data.service.TextToSpeechRepository
import com.aaron.raleytime.framework.baseviewmodel.BaseViewModel
import com.aaron.raleytime.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppTtsScreenViewModel @Inject constructor(
    dataRepository: DataRepository,
    private val textToSpeechRepository: TextToSpeechRepository
) :
    BaseViewModel(dataRepository) {
    private val _screenState = MutableStateFlow(getInitialData())
    val screenState = _screenState.asStateFlow()


    init {
        observeTtsState()
    }

    private fun getInitialData(): AppTtsScreenUIStates {
        return AppTtsScreenUIStates(
            appTtsScreenHeaderTitle = "Speak Your Text"
        )
    }

    private fun observeTtsState() {
        viewModelScope.launch {
            textToSpeechRepository.observeTtsState().collect { ttsState ->
                _screenState.update { currentState ->
                    currentState.copy(appTtsState = ttsState)
                }
            }
        }
    }

    fun onAppTtsScreenEvents(event: AppTtsScreenUIEvents) {
        when (event) {
            is AppTtsScreenUIEvents.OnTextChanged -> {
                _screenState.update { it.copy(appTtsInputText = event.text) }
            }

            is AppTtsScreenUIEvents.Speak -> {
                val text = _screenState.value.appTtsInputText
                textToSpeechRepository.speak(text)
            }

            is AppTtsScreenUIEvents.Pause -> {
                textToSpeechRepository.pause()
            }

            is AppTtsScreenUIEvents.Resume -> {
                textToSpeechRepository.resume()
            }

            is AppTtsScreenUIEvents.Stop -> {
                textToSpeechRepository.stop()
            }

            is AppTtsScreenUIEvents.OnSpeechRateChanged -> {
                textToSpeechRepository.setSpeechRate(event.rate)
            }

            is AppTtsScreenUIEvents.OnPitchChanged -> {
                textToSpeechRepository.setPitch(event.pitch)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        textToSpeechRepository.cleanup()
    }
}

