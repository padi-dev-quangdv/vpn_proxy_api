package com.midterm.securevpnproxy.domain.model

data class LoginModel(
    val email: String?,
    val accessToken: String?,
    val refreshToken: String?,
)
