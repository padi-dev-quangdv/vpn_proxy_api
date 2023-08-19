package com.tanify.library.authentication.domain.usecase.reset_password

import com.tanify.library.libcore.usecase.FlowResultUseCase
import com.tanify.library.libcore.usecase.ResultModel
import kotlinx.coroutines.flow.Flow


interface ResetPasswordUseCase: FlowResultUseCase<ResetPasswordParam, Boolean>
