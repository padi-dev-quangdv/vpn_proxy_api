package com.midterm.securevpnproxy.data.repository.network

import com.midterm.securevpnproxy.data.dto.LoginDto
import com.midterm.securevpnproxy.data.dto.RegisterDto
import com.midterm.securevpnproxy.data.repository.network.body.LoginBody
import com.midterm.securevpnproxy.domain.payload.LoginPayload
import com.midterm.securevpnproxy.domain.payload.RegisterPayload
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AppApiService {
    @POST("auth/login")
    suspend fun login(@Body payload: LoginBody): LoginDto

    @POST("auth/register")
    suspend fun register(@Body payload: RegisterPayload): Call<RegisterDto>
}