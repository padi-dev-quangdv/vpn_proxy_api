package com.tanify.library.authentication.data.repository.network.body


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterBody(
    @Json(name = "email")
    val email: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "password")
    val password: String?
)