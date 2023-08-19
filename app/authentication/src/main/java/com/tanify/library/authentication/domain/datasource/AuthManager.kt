package com.tanify.library.authentication.domain.datasource

import com.tanify.library.authentication.domain.model.auth_state.AuthState
import kotlinx.coroutines.flow.Flow

interface AuthManager {
    fun getAuthState(): Flow<AuthState>
//
//    suspend fun getAccessToken(): String
//
//    suspend fun setAccessToken(token: String?)
//
//    suspend fun getRefreshToken(): String
//
//    suspend fun setRefreshToken(token: String?)
//
//    suspend fun setUserData(userData: UserDataModel?)
//
//    suspend fun getUserData(): UserDataModel?
}