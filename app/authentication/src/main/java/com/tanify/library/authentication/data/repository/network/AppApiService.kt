//package com.tanify.library.authentication.data.repository.network
//
//import com.tanify.library.authentication.data.dto.LoginDto
//import com.tanify.library.authentication.data.dto.RegisterDto
//import com.tanify.library.authentication.data.repository.network.body.LoginBody
//import com.tanify.library.authentication.data.repository.network.body.RegisterBody
//import retrofit2.http.Body
//import retrofit2.http.POST
//
//interface AppApiService {
//    @POST("auth/login")
//    suspend fun login(@Body payload: LoginBody): LoginDto
//
//    @POST("auth/register")
//    suspend fun register(@Body payload: RegisterBody): RegisterDto
//}