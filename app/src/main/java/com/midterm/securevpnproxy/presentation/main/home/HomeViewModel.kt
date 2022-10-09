package com.midterm.securevpnproxy.presentation.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class HomeViewModel: ViewModel() {
    val viewState = MutableLiveData(ViewState())
    val timer: MutableLiveData<Timer> = MutableLiveData(Timer())
    val timerTask: MutableLiveData<TimerTask> = MutableLiveData()
    val time: MutableLiveData<Double> = MutableLiveData(0.0)

     fun startTimer() {
         timerTask.value = object : TimerTask() {
             override fun run() {
                 viewModelScope.launch {
                     time.value?.inc()
                 }
             }

         }
     }

    fun onEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.OnOffToggle -> {
                val currentOnOffState = viewState.value?.onOffState ?: false
                viewState.postValue(viewState.value?.copy(onOffState = !currentOnOffState))
            }
        }
    }

    data class ViewState(
        val onOffState: Boolean = false
    )


    sealed interface ViewEvent {
        object OnOffToggle: ViewEvent
    }

}