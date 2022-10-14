package com.midterm.securevpnproxy.presentation.main.home

import androidx.lifecycle.MutableLiveData
import com.midterm.securevpnproxy.base.BaseViewModel

class HomeViewModel : BaseViewModel<HomeViewModel.ViewState,HomeViewModel.ViewEvent>() {

    init {
        viewState = MutableLiveData(ViewState())
    }

    override fun onEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.OnOffToggle -> {
                val currentOnOffState = viewState.value?.onOffState ?: false
                viewState.postValue(viewState.value?.copy(onOffState = !currentOnOffState))
            }
        }
    }

    data class ViewState(
        val onOffState: Boolean = false
    ) : BaseViewModel.ViewState()


    sealed interface ViewEvent : BaseViewModel.ViewEvent {
        object OnOffToggle : ViewEvent
    }
}