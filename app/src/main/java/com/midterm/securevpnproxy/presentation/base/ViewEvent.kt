package com.midterm.securevpnproxy.presentation.base

interface ViewEvent {
    data class ResetPasswordEvent(
        val email: String
    ) : ViewEvent
    data class LoginEvent(
        val email: String,
        val password: String
    ): ViewEvent
}