package com.midterm.securevpnproxy.presentation

import com.midterm.securevpnproxy.presentation.login.ForgotPasswordViewModel
import com.midterm.securevpnproxy.presentation.login.LoginViewModel
import com.midterm.securevpnproxy.presentation.register.RegisterViewModel

sealed interface ViewEvent {
    data class ResetPasswordEvent(
        val email: String
    ) : ViewEvent
    data class RegisterEvent(
        val fullName: String,
        val email: String,
        val password: String,
        val confirmPassword: String
    ) : ViewEvent
    data class LoginEvent(
        val email: String,
        val password: String
    ): ViewEvent
}