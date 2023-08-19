package com.tanify.library.authentication.domain.usecase.register

import com.tanify.library.authentication.domain.model.register.RegisterModel
import com.tanify.library.libcore.usecase.FlowResultUseCase
import com.tanify.library.libcore.usecase.ResultModel
import kotlinx.coroutines.flow.Flow

interface RegisterUseCase : FlowResultUseCase<RegisterParam, RegisterModel>