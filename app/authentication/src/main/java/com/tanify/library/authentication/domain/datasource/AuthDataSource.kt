package com.tanify.library.authentication.domain.datasource

import com.tanify.library.libcore.usecase.ResultModel
import com.tanify.library.authentication.domain.model.login.LoginModel
import com.tanify.library.authentication.domain.model.register.RegisterModel
import com.tanify.library.authentication.domain.model.user_info.UserDataModel
import com.tanify.library.authentication.domain.payload.LoginPayload
import com.tanify.library.authentication.domain.payload.RegisterPayload
import com.tanify.library.authentication.domain.usecase.reset_password.ResetPasswordParam
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {

    fun loginWithEmailPass(payload: LoginPayload): Flow<ResultModel<LoginModel>>

    fun registerWithEmailPassword(payload: RegisterPayload): Flow<ResultModel<RegisterModel>>

    fun resetPassword(param: ResetPasswordParam): Flow<ResultModel<Boolean>>

    fun getUserInfo(): Flow<ResultModel<UserDataModel>>

    fun subscribeUserInfo()
}