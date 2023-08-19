package com.tanify.library.authentication.data.dto.auth_state

enum class AuthState {
    LOGGED_IN,
    LOGGED_OUT,
}

fun AuthState?.toModel(): com.tanify.library.authentication.domain.model.auth_state.AuthState {
    return when (this) {
        AuthState.LOGGED_IN -> com.tanify.library.authentication.domain.model.auth_state.AuthState.LOGGED_IN
        AuthState.LOGGED_OUT -> com.tanify.library.authentication.domain.model.auth_state.AuthState.LOGGED_OUT
        else -> com.tanify.library.authentication.domain.model.auth_state.AuthState.LOGGED_OUT
    }
}