package com.midterm.securevpnproxy.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<STATE: BaseViewModel.ViewState>: ViewModel() {
    lateinit var viewState: MutableLiveData<STATE>

    open class ViewState

    interface ViewEvent

    abstract fun onEvent(event: ViewEvent)
}