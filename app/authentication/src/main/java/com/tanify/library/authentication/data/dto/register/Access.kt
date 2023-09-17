package com.tanify.library.authentication.data.dto.register


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Access(
    @Json(name = "expires")
    val expires: String?,
    @Json(name = "token")
    val token: String?
)