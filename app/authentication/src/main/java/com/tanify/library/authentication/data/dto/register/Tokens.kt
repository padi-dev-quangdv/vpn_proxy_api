package com.tanify.library.authentication.data.dto.register


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tokens(
    @Json(name = "access")
    val access: Access?,
    @Json(name = "refresh")
    val refresh: Refresh?
)