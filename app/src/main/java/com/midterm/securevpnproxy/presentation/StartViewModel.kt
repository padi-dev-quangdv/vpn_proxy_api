package com.midterm.securevpnproxy.presentation

import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.presentation.StartViewModel.ViewEffect
import com.midterm.securevpnproxy.presentation.StartViewModel.ViewEvent
import com.midterm.securevpnproxy.presentation.StartViewModel.ViewState
import com.tanify.library.authentication.domain.model.auth_state.AuthState
import com.tanify.library.authentication.domain.usecase.auth_state.AuthStateUseCase
import com.tanify.library.libcore.usecase.ResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Objects

@HiltViewModel
class StartViewModel @Inject constructor(
) : BaseViewModel<ViewState, ViewEvent, ViewEffect>(ViewState()) {
    override fun onEvent(event: ViewEvent) {
    }

    class ViewState : BaseViewState

    sealed interface ViewEvent : BaseViewEvent

    sealed interface ViewEffect : BaseViewEffect {
        data class Error(val message: String) : ViewEffect
    }

}