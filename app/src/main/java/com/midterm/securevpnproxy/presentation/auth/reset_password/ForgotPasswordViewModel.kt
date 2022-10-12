package com.midterm.securevpnproxy.presentation.auth.reset_password

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.domain.usecase.reset_password.ResetPasswordParam
import com.midterm.securevpnproxy.domain.usecase.reset_password.ResetPasswordUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val resetPasswordUseCase: ResetPasswordUseCaseImpl) :
    BaseViewModel<ForgotPasswordViewModel.ViewState>() {

    init {
        viewState = MutableLiveData(ViewState())
    }

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
            viewState.postValue(
                viewState.value?.copy(
                    emailError = "Invalid email"
                )
            )
            return false
        }
        return true
    }

    data class ViewState(
        val emailError: String? = null
    ) : BaseViewModel.ViewState()

    sealed interface ViewEvent {
        data class ResetPasswordEvent(
            val email: String
        ) : BaseViewModel.ViewEvent
    }

    override fun onEvent(event: BaseViewModel.ViewEvent) {
        when (event) {
            is ViewEvent.ResetPasswordEvent -> resetPassword(event.email)
        }
    }

}