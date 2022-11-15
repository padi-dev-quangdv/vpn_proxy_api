package com.midterm.securevpnproxy.presentation.auth.reset_password

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.domain.usecase.reset_password.ResetPasswordParam
import com.midterm.securevpnproxy.domain.usecase.reset_password.ResetPasswordUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val resetPasswordUseCase: ResetPasswordUseCaseImpl) :
    BaseViewModel<ForgotPasswordViewModel.ViewState, ForgotPasswordViewModel.ViewEvent, ForgotPasswordViewModel.ViewEffect>(
        ViewState()
    ) {

    val isEmailExist: MutableLiveData<Boolean> = MutableLiveData()

    private fun resetPassword(email: String) {
        val validateEmail = validateEmail(email)
        if (!validateEmail) {
            isEmailExist.value = false
            return
        } else {
            isEmailExist.value = true
            viewModelScope.launch {
                resetPasswordUseCase(ResetPasswordParam(email))
            }
        }
    }

    fun doneNavigating() {
        isEmailExist.value = false
    }

    private fun validateEmail(email: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            setState(
                currentState.copy(
                    emailError = "Invalid email"
                )
            )
            return false
        }
        return true
    }

    data class ViewState(
        val emailError: String? = null
    ) : BaseViewState

    sealed interface ViewEvent : BaseViewEvent {
        data class ResetPasswordEvent(
            val email: String
        ) : ViewEvent
    }

    override fun onEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.ResetPasswordEvent -> resetPassword(event.email)
        }
    }

    interface ViewEffect : BaseViewEffect

}