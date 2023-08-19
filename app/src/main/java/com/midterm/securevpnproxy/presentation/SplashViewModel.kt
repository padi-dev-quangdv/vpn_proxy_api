package com.midterm.securevpnproxy.presentation

import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.tanify.library.authentication.domain.model.auth_state.AuthState
import com.tanify.library.authentication.domain.usecase.auth_state.AuthStateUseCase
import com.tanify.library.libcore.usecase.ResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authStateUseCase: AuthStateUseCase,

    ) :
    BaseViewModel<SplashViewModel.ViewState, SplashViewModel.ViewEvent, SplashViewModel.ViewEffect>(
        ViewState()
    ) {

    private var authStateJob: Job? = null

    init {
        checkAuthState()
    }

    private fun checkAuthState() {
        authStateJob?.cancel()
        authStateJob = coroutineScope.launch {
            authStateUseCase.execute(Any()).collectLatest {
                if (it is ResultModel.Success) {
                    setState(currentState.copy(authState = it.result))
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        authStateJob?.cancel()
    }

    override fun onEvent(viewEvent: ViewEvent) {

    }

    data class ViewState(
        val authState: AuthState? = null
    ) : BaseViewState

    sealed interface ViewEvent : BaseViewEvent

    sealed interface ViewEffect : BaseViewEffect
}
