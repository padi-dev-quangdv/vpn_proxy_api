package com.tanify.library.authentication.domain.usecase.auth_state

import com.tanify.library.authentication.domain.model.auth_state.AuthState
import com.tanify.library.libcore.usecase.FlowResultUseCase
import kotlinx.coroutines.flow.Flow

interface AuthStateUseCase: FlowResultUseCase<Any, AuthState>