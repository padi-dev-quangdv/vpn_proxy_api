package com.midterm.securevpnproxy.presentation.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midterm.securevpnproxy.domain.usecase.reset_password.ResetPasswordUseCaseImpl
import com.midterm.securevpnproxy.presentation.ViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val resetPasswordUseCase: ResetPasswordUseCaseImpl): ViewModel() {

    val viewState: MutableLiveData<ViewState> = MutableLiveData(ViewState())
    val isEmailExist: MutableLiveData<Boolean> = MutableLiveData()

    private fun resetPassword(email: String) {
        val validateEmail = validateEmail(email)
        if(!validateEmail) {
            isEmailExist.value = false
            return
        }
        else {
            isEmailExist.value = true
            viewModelScope.launch {
                resetPasswordUseCase(email)
            }
        }
    }

    fun doneNavigating() {
        isEmailExist.value = false
    }

    private fun validateEmail(email: String): Boolean{
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            viewState.postValue(viewState.value?.copy(
                emailError = "Invalid email"
            ))
            return false
        }
        return true
    }

    fun onEvent(event: ViewEvent) {
        when(event) {
            is ViewEvent.ResetPasswordEvent -> resetPassword(event.email)
        }
    }

    data class ViewState (
        val emailError: String? = null
    )

}