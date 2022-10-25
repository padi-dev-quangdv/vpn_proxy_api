package com.midterm.securevpnproxy.presentation.main.account_info

import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.domain.usecase.check_login.CheckLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountInformationViewModel @Inject constructor
    (private val checkLoginUseCase: CheckLoginUseCase) :
    BaseViewModel<AccountInformationViewModel.ViewState, AccountInformationViewModel.ViewEvent>() {


    fun checkLogout() {
        checkLoginUseCase.checkLogout()
    }

    override fun onEvent(event: AccountInformationViewModel.ViewEvent) {
        TODO("Not yet implemented")
    }

    class ViewState(): BaseViewModel.ViewState()

    sealed interface ViewEvent: BaseViewModel.ViewEvent {

    }


}