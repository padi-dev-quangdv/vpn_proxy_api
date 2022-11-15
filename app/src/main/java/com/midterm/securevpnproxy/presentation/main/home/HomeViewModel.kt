package com.midterm.securevpnproxy.presentation.main.home

import androidx.lifecycle.MutableLiveData
import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState

class HomeViewModel : BaseViewModel<HomeViewModel.ViewState, HomeViewModel.ViewEvent, HomeViewModel.ViewEffect>(ViewState()) {

    override fun onEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.OnOffToggle -> {
                val currentOnOffState = currentState.onOffState
                setState(currentState.copy(onOffState = !currentOnOffState))
            }
        }
    }

    data class ViewState(
        val onOffState: Boolean = false
    ) : BaseViewState


    sealed interface ViewEvent : BaseViewEvent {
        object OnOffToggle : ViewEvent
    }

    sealed interface ViewEffect : BaseViewEffect
}