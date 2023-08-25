package com.midterm.securevpnproxy.presentation

import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.presentation.MainActivityViewModel.*
import com.tanify.library.authentication.domain.datasource.AuthManager
import com.tanify.library.authentication.domain.model.auth_state.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val authManager: AuthManager,
) : BaseViewModel<ViewState, ViewEvent, ViewEffect>(ViewState()) {

    init {
        listenAuthState()
    }

    private fun listenAuthState() {
        authManager.getAuthState()
            .distinctUntilChanged()
            .onEach(::handleAuthStateChanged)
            .launchIn(coroutineScope)
    }

    private fun handleAuthStateChanged(it: AuthState) {
        when (it) {
            AuthState.LOGGED_IN -> handleUserLoggedIn()
            AuthState.LOGGED_OUT -> handleUserLoggedOut()
        }
    }

    private fun handleUserLoggedOut() {
        // un listen data changes
    }

    private fun handleUserLoggedIn() {
        // listen data changes
    }

    override fun onEvent(viewEvent: ViewEvent) {

    }

    class ViewState : BaseViewState

    sealed interface ViewEvent : BaseViewEvent

    sealed interface ViewEffect : BaseViewEffect
}
