package com.tanify.library.authentication.data.repository.network

import com.tanify.library.authentication.data.dto.register.RegisterDto
import com.tanify.library.authentication.data.repository.network.body.RegisterBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("auth/register")
    suspend fun register(@Body body: RegisterBody): RegisterDto
}