package com.midterm.securevpnproxy.presentation.register

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midterm.securevpnproxy.domain.usecase.register.RegisterParam
import com.midterm.securevpnproxy.domain.usecase.register.RegisterUseCaseImpl
import com.midterm.securevpnproxy.presentation.base.ViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject
constructor(private val registerUseCase: RegisterUseCaseImpl) : ViewModel() {

    val viewState: MutableLiveData<ViewState> = MutableLiveData(ViewState())

    private fun register(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        val validateResult = validateRegister(fullName, email, password, confirmPassword)
        if (!validateResult) {
            return
        } else {
            viewModelScope.launch {
                registerUseCase(
                    RegisterParam(
                        email = email,
                        fullName = fullName,
                        password = password
                    )
                )
            }
        }
    }

    private fun validateRegister(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (fullName.trim().isEmpty()) {
            viewState.postValue(
                viewState.value?.copy(
                    fullNameError = "Invalid fullname"
                )
            )
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            viewState.postValue(
                viewState.value?.copy(
                    emailError = "Invalid email"
                )
            )
            return false
        }
        if (password.trim().length < 8) {
            viewState.postValue(
                viewState.value?.copy(
                    passwordError = "Password must be longer than 8"
                )
            )
            return false
        }
        if (confirmPassword.trim() != password.trim()) {
            viewState.postValue(
                viewState.value?.copy(
                    confirmPasswordError = "Confirm password is incorrect"
                )
            )
            return false
        }
        return true
    }

    fun onEvent(event: ViewEvent) {
        when (event) {
            is RegisterEvent -> register(
                event.fullName,
                event.email,
                event.password,
                event.confirmPassword
            )
        }
    }

    data class ViewState(
        val fullNameError: String? = null,
        val emailError: String? = null,
        val passwordError: String? = null,
        val confirmPasswordError: String? = null
    )

    data class RegisterEvent(
        val fullName: String,
        val email: String,
        val password: String,
        val confirmPassword: String
    ) : ViewEvent


    sealed interface ViewEffect {

    }
}