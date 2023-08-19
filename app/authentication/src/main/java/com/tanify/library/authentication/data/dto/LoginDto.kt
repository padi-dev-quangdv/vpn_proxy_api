//package com.tanify.library.authentication.data.dto
//
//import com.midterm.securevpnproxy.domain.model.LoginModel
//import com.squareup.moshi.Json
//import com.squareup.moshi.JsonClass
//import com.tanify.library.authentication.domain.model.login.LoginModel
//
//@JsonClass(generateAdapter = true)
//data class LoginDto(
//    @Json(name = "email")
//    val email: String?,
//    @Json(name = "access_token")
//    val accessToken: String?,
//    @Json(name = "refresh_token")
//    val refreshToken: String?,
//) {
//    fun toModel(): LoginModel {
//        return LoginModel(email = email, accessToken = accessToken, refreshToken = refreshToken)
//    }
//}
