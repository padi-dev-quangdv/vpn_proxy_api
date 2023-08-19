//package com.tanify.library.authentication.data.dto
//
//
//import com.midterm.securevpnproxy.domain.model.RegisterModel
//import com.squareup.moshi.Json
//import com.squareup.moshi.JsonClass
//
//@JsonClass(generateAdapter = true)
//data class RegisterDto(
//    @Json(name = "access_token")
//    val accessToken: String,
//    @Json(name = "email")
//    val email: String,
//    @Json(name = "fullName")
//    val fullName: String,
//    @Json(name = "id")
//    val id: String,
//    @Json(name = "isActive")
//    val isActive: Boolean,
//    @Json(name = "refresh_token")
//    val refreshToken: String
//) {
//    fun toModel(): RegisterModel {
//        return RegisterModel(
//            accessToken = accessToken,
//            email = email,
//            fullName = fullName,
//            id = id,
//            isActive = isActive,
//            refreshToken = refreshToken
//        )
//    }
//}