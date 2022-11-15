package com.midterm.securevpnproxy.presentation.main.account_info

import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.domain.usecase.check_login.CheckLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountInformationViewModel @Inject constructor
    (private val checkLoginUseCase: CheckLoginUseCase) :
    BaseViewModel<AccountInformationViewModel.ViewState, AccountInformationViewModel.ViewEvent, AccountInformationViewModel.ViewEffect>(
        ViewState()
    ) {


    fun checkLogout() {
        checkLoginUseCase.checkLogout()
    }

    override fun onEvent(event: AccountInformationViewModel.ViewEvent) {
        TODO("Not yet implemented")
    }

    class ViewState(): BaseViewState

    sealed interface ViewEvent: BaseViewEvent {

    }

    sealed interface ViewEffect: BaseViewEffect

}