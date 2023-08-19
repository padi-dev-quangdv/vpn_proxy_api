package com.midterm.securevpnproxy.presentation.main.profile

import androidx.lifecycle.ViewModel
import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.presentation.ui_model.UiUserDataModel
import com.tanify.library.authentication.domain.model.user_info.UserDataModel
import com.tanify.library.authentication.domain.usecase.get_user_info.GetUserInfoUseCase
import com.tanify.library.libcore.usecase.ResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userInfoUseCase: GetUserInfoUseCase
) : BaseViewModel<ProfileViewModel.ViewState, ProfileViewModel.ViewEvent, ProfileViewModel.ViewEffect>(
    ViewState()
) {

    private var userInfoJob: Job? = null

    init {
        listenUserInfo()
    }

    private fun listenUserInfo() {
        userInfoJob?.cancel()
        userInfoJob = userInfoUseCase.execute(Any()).onEach {
            Timber.d("==> $it")
            when (it) {
                is ResultModel.Success -> {
                    setState(currentState.copy(userModel = UiUserDataModel.fromDomainModel(it.result)))
                }
            }
        }.launchIn(coroutineScope)
    }

    override fun onCleared() {
        super.onCleared()
        userInfoJob?.cancel()
    }

    override fun onEvent(viewEvent: ViewEvent) {

    }

    data class ViewState(
        val userModel: UiUserDataModel? = null
    ) : BaseViewState

    sealed interface ViewEvent : BaseViewEvent

    sealed interface ViewEffect : BaseViewEffect
}
