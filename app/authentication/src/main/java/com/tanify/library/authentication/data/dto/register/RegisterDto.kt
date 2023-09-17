package com.tanify.library.authentication.data.dto.register


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterDto(
    @Json(name = "tokens")
    val tokens: Tokens?,
    @Json(name = "user")
    val user: User?
)