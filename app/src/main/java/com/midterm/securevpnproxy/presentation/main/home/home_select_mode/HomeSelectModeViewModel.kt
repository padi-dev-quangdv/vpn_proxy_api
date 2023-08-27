package com.midterm.securevpnproxy.presentation.main.home.home_select_mode

import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.presentation.main.home.home_select_mode.HomeSelectModeViewModel.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeSelectModeViewModel @Inject constructor(

) : BaseViewModel<ViewState, ViewEvent, ViewEffect>(
    ViewState()
) {

    override fun onEvent(viewEvent: ViewEvent) {

    }

    class ViewState : BaseViewState

    sealed interface ViewEvent : BaseViewEvent

    sealed interface ViewEffect : BaseViewEffect
}
