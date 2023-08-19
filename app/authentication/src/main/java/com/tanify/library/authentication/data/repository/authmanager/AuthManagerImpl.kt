package com.tanify.library.authentication.data.repository.authmanager

import com.google.firebase.auth.FirebaseAuth
import com.tanify.library.authentication.data.dto.auth_state.AuthState
import com.tanify.library.authentication.data.dto.auth_state.toModel
import com.tanify.library.authentication.domain.datasource.AuthManager
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AuthManagerImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthManager {

    private val authStateSharedFlow: MutableSharedFlow<AuthState> = MutableSharedFlow(replay = 1)

    init {
        subscribeFirebaseAuthState()
    }

    private fun subscribeFirebaseAuthState() {
        auth.addAuthStateListener {
            if (it.currentUser != null) {
                authStateSharedFlow.tryEmit(AuthState.LOGGED_IN)
            } else {
                authStateSharedFlow.tryEmit(AuthState.LOGGED_OUT)
            }
        }
    }

    override fun getAuthState(): Flow<com.tanify.library.authentication.domain.model.auth_state.AuthState> =
        flow {
            emitAll(authStateSharedFlow.map { it.toModel() })
        }
}