package com.midterm.securevpnproxy.presentation.main.account_info

import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.presentation.main.account_info.AccountInformationViewModel.ViewEffect
import com.midterm.securevpnproxy.presentation.main.account_info.AccountInformationViewModel.ViewEvent
import com.midterm.securevpnproxy.presentation.main.account_info.AccountInformationViewModel.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountInformationViewModel @Inject constructor() :
    BaseViewModel<ViewState, ViewEvent, ViewEffect>(
        ViewState()
    ) {


    fun checkLogout() {
//        checkLoginUseCase.checkLogout()
    }

    override fun onEvent(event: ViewEvent) {
        TODO("Not yet implemented")
    }

    class ViewState : BaseViewState

    sealed interface ViewEvent : BaseViewEvent

    sealed interface ViewEffect : BaseViewEffect

}