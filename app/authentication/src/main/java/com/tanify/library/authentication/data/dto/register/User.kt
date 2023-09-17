package com.tanify.library.authentication.data.dto.register


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "email")
    val email: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "isEmailVerified")
    val isEmailVerified: Boolean?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "role")
    val role: String?
)