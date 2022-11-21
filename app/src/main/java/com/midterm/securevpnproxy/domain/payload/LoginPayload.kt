package com.midterm.securevpnproxy.domain.payload

data class LoginPayload(
    val email: String,
    val password: String,
)
