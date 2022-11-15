package com.midterm.securevpnproxy.presentation.auth.register

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.usecase.register.RegisterParam
import com.midterm.securevpnproxy.domain.usecase.register.RegisterUseCase
import com.midterm.securevpnproxy.presentation.auth.login.LoginViewModel
import com.midterm.securevpnproxy.presentation.auth.register.RegisterViewModel.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject
constructor(private val registerUseCase: RegisterUseCase) :
    BaseViewModel<ViewState, ViewEvent, ViewEffect>(ViewState()) {

    private var registerJob: Job? = null

    private fun register(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        val validateResult = validateRegister(fullName, email, password, confirmPassword)
        if (!validateResult) return
        registerJob?.cancel()
        val param = RegisterParam(email = email, fullName = fullName, password = password)
        registerJob = viewModelScope.launch {
            registerUseCase(param).collectLatest { result ->
                when (result) {
                    is ResultModel.Success -> {
                        setEffect(ViewEffect.RegisterCompleted)
                    }
                    is ResultModel.Error -> {
                        setEffect(ViewEffect.Error(message = result.t.localizedMessage ?: "Unknown Error"))
                    }
                }
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
            setState(
                currentState.copy(
                    fullNameError = "Full name cannot be empty"
                )
            )
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            setState(
                currentState.copy(
                    emailError = "Invalid email"
                )
            )
            return false
        }
        if (password.trim().isEmpty()) {
            setState(
                currentState.copy(
                    passwordError = "Password cannot be empty"

                )
            )
        }
        if (password.trim().length < 8) {
            setState(
                currentState.copy(
                    passwordError = "Password must be longer than 8"

                )
            )
            return false
        }
        if (confirmPassword.trim() != password.trim()) {
            setState(
                currentState.copy(
                    confirmPasswordError = "Confirm password is incorrect"
                )
            )
            return false
        }
        return true
    }

    override fun onEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.RegisterEvent -> register(
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
    ) : BaseViewState

    sealed interface ViewEvent : BaseViewEvent {
        data class RegisterEvent(
            val fullName: String,
            val email: String,
            val password: String,
            val confirmPassword: String
        ) : ViewEvent
    }


    sealed interface ViewEffect : BaseViewEffect {
        object RegisterCompleted: ViewEffect
        data class Error(val message: String): ViewEffect
    }
}