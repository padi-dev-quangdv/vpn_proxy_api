package com.tanify.library.authentication.domain.usecase.login

import com.tanify.library.authentication.domain.model.login.LoginModel
import com.tanify.library.libcore.usecase.FlowResultUseCase
import com.tanify.library.libcore.usecase.ResultModel
import kotlinx.coroutines.flow.Flow

interface LoginUseCase: FlowResultUseCase<LoginParam, Unit>