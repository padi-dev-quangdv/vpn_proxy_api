package com.midterm.securevpnproxy.presentation.auth.register

import android.util.Patterns
import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.presentation.auth.login.LoginViewModel
import com.midterm.securevpnproxy.presentation.auth.register.RegisterViewModel.ViewEffect
import com.midterm.securevpnproxy.presentation.auth.register.RegisterViewModel.ViewEvent
import com.midterm.securevpnproxy.presentation.auth.register.RegisterViewModel.ViewState
import com.tanify.library.authentication.domain.usecase.register.RegisterParam
import com.tanify.library.authentication.domain.usecase.register.RegisterUseCase
import com.tanify.library.libcore.usecase.ResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel<ViewState, ViewEvent, ViewEffect>(ViewState()) {

    private var registerJob: Job? = null

    private fun register() {
        val fullName = currentState.fullName
        val email = currentState.email
        val password = currentState.password
        val confirmPassword = currentState.confirmPassword
        val validateResult = validateRegister(fullName, email, password, confirmPassword)
        if (!validateResult) return
        registerJob?.cancel()
        val param = RegisterParam(
            email = email,
            fullName = fullName,
            password = password
        )
        onLoading(true)
        registerJob = registerUseCase.execute(param).onEach { result ->
            onLoading(false)
            when (result) {
                is ResultModel.Success -> {
                    setEffect(ViewEffect.RegisterCompleted)
                }

                is ResultModel.Error -> {
                    setEffect(
                        ViewEffect.Error(
                            message = result.t.localizedMessage ?: "Unknown Error"
                        )
                    )
                }
            }
        }.launchIn(coroutineScope)
    }

    private fun validateRegister(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String,
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
        //clear
        setState(
            currentState.copy(
                fullNameError = null,
                emailError = null,
                passwordError = null,
                confirmPasswordError = null
            )
        )
        return true
    }

    override fun onEvent(event: ViewEvent) {
        when (event) {
            ViewEvent.Submit -> register()
            ViewEvent.TogglePasswordVisibility -> updatePasswordVisibility()
            is ViewEvent.UpdateConfirmPassword -> updateConfirmPassword(event.confirmPassword)
            is ViewEvent.UpdateEmail -> updateEmail(event.email)
            is ViewEvent.UpdateFullName -> updateFullName(event.fullName)
            is ViewEvent.UpdatePassword -> updatePassword(event.password)
        }
    }

    private fun updateFullName(fullName: String) {
        setState(currentState.copy(fullName = fullName))
    }

    private fun updateEmail(email: String) {
        setState(currentState.copy(email = email))
    }

    private fun updatePassword(password: String) {
        setState(currentState.copy(password = password))
    }

    private fun updateConfirmPassword(password: String) {
        setState(currentState.copy(confirmPassword = password))
    }

    private fun updatePasswordVisibility() {
        setState(currentState.copy(showPassword = !currentState.showPassword))
    }

    data class ViewState(
        val fullName: String = "",
        val fullNamePlaceholder: String = "John Doe",
        val fullNameError: String? = null,
        val email: String = "",
        val emailPlaceholder: String = "email@example.com",
        val emailError: String? = null,
        val password: String = "",
        val passwordPlaceholder: String = "********",
        val passwordError: String? = null,
        val confirmPassword: String = "",
        val confirmPasswordError: String? = null,
        val showPassword: Boolean = false,
    ) : BaseViewState

    sealed interface ViewEvent : BaseViewEvent {
        data class UpdateFullName(val fullName: String) : ViewEvent
        data class UpdateEmail(val email: String) : ViewEvent
        data class UpdatePassword(val password: String) : ViewEvent
        data class UpdateConfirmPassword(val confirmPassword: String) : ViewEvent
        object TogglePasswordVisibility : ViewEvent
        object Submit : ViewEvent
    }


    sealed interface ViewEffect : BaseViewEffect {
        object RegisterCompleted : ViewEffect
        data class Error(val message: String) : ViewEffect
    }
}