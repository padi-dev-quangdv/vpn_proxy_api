package com.midterm.securevpnproxy.data.repository

import android.app.Application
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.midterm.securevpnproxy.data.dto.LoginDto
import com.midterm.securevpnproxy.data.repository.network.AppApiService
import com.midterm.securevpnproxy.data.repository.network.body.LoginBody
import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.RegisterModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.payload.LoginPayload
import com.midterm.securevpnproxy.domain.payload.RegisterPayload
import com.midterm.securevpnproxy.domain.usecase.reset_password.ResetPasswordParam
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val application: Application,
    private val appApiService: AppApiService,
    private val moshi: Moshi,
) : AuthDataSource {

    companion object {
        const val SHARED_PREFS = "sharedPrefs"
        private const val USER_DATA = "USER_DATA"
        private const val LOGGED_IN = "LOGGED_IN"
    }

    private val sharedPreference by lazy {
        application.getSharedPreferences(SHARED_PREFS, 0)
    }

    private fun saveUserDataToStorage(loginDto: LoginDto) {
        sharedPreference.edit().apply {
            val jsonAdapter = moshi.adapter(LoginDto::class.java)
            this.putString(USER_DATA, jsonAdapter.toJson(loginDto))
        }.apply()
    }

    private fun getUserDataFromStorage(): LoginDto? {
        val userDataString = sharedPreference.getString(USER_DATA, null)
        val jsonAdapter = moshi.adapter(LoginDto::class.java)
        return jsonAdapter.fromJson(userDataString)
    }

    private fun saveLoginState(loggedIn: Boolean) {
        sharedPreference.edit().apply {
            this.putBoolean(LOGGED_IN, loggedIn)
        }.apply()
    }

    override fun isLogin(): Boolean {
        return sharedPreference.getBoolean(LOGGED_IN, false)
    }

    override fun login(payload: LoginPayload): Flow<ResultModel<LoginModel>> {
        return flow {
            val result = try {
                val body = LoginBody(email = payload.email, password = payload.password)
                val result = appApiService.login(body)
                // success
                val model = result.toLoginModel()
                // save user info (userData + login State)
                saveUserDataToStorage(result)
                saveLoginState(loggedIn = true)
                ResultModel.Success(model)
            } catch (t: Throwable) {
                t.printStackTrace()
                ResultModel.Error(t)
            }
            emit(result)
        }
    }

    override fun getCurrentUser(): Flow<ResultModel<LoginModel>> {
        return flow {
            try {
                val userData = getUserDataFromStorage()
                if (userData != null) {
                    val loginModel = userData.toLoginModel()
                    emit(ResultModel.Success(loginModel))
                } else {
                    emit(ResultModel.Error(java.lang.Exception("User Not Found")))
                }
            } catch (t: Throwable) {
                t.printStackTrace()
                emit(ResultModel.Error(t))
            }
        }
    }

    override fun register(payload: RegisterPayload): Flow<ResultModel<RegisterModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword(param: ResetPasswordParam) {
        Firebase.auth.sendPasswordResetEmail(param.email)
    }

}