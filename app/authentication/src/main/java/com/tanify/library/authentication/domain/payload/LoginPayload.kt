package com.tanify.library.authentication.domain.payload

data class LoginPayload(
    val email: String,
    val password: String,
)
