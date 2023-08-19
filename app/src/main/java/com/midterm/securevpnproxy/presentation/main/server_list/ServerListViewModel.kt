package com.midterm.securevpnproxy.presentation.main.server_list

import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.presentation.main.server_list.ServerListViewModel.ViewEffect
import com.midterm.securevpnproxy.presentation.main.server_list.ServerListViewModel.ViewEvent
import com.midterm.securevpnproxy.presentation.main.server_list.ServerListViewModel.ViewState
import com.tanify.library.dns.domain.model.server_list.ServerGroupType
import com.tanify.library.dns.domain.model.server_list.ServerModel
import com.tanify.library.dns.domain.usecase.server_list.ServerListParam
import com.tanify.library.dns.domain.usecase.server_list.ServerListUseCase
import com.tanify.library.libcore.usecase.ResultModel
import com.tanify.library.localdb.tanify.UserDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class ServerListViewModel @Inject constructor(
    private val serverListUseCase: ServerListUseCase,
    private val userDataStore: UserDataStore,
) : BaseViewModel<ViewState, ViewEvent, ViewEffect>(
    ViewState()
) {
    private var getServerListJob: Job? = null
    private var getDefaultGroupTypeJob: Job? = null

    init {
        initServerList()
        getDefaultGroupType()
    }

    private fun getDefaultGroupType() {
        getDefaultGroupTypeJob?.cancel()
        getDefaultGroupTypeJob = userDataStore.getSelectedGroupType().onEach { typeId ->
            val groupType =
                ServerGroupType.values().firstOrNull { it.id == typeId }
            setState(currentState.copy(selectedGroupType = groupType))
        }.launchIn(coroutineScope)
    }

    private fun setDefaultGroupType(type: String) {
        coroutineScope.launch {
            userDataStore.saveSelectedGroupType(type)
        }
    }

    private fun initServerList() {
        getServerListJob?.cancel()
        getServerListJob = serverListUseCase.execute(ServerListParam())
            .onEach {
                if (it is ResultModel.Success) {
                    handleServerList(it.result)
                }
            }
            .launchIn(coroutineScope)
    }

    private fun handleServerList(result: List<ServerModel>) {
        val data = result.map {
            it.groupType
        }.distinctBy {
            it.id
        }
        setState(currentState.copy(groupTypes = data))
    }

    override fun onEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.SelectServerGroupType -> handleUserSelectGroupType(event.type)
        }
    }

    private fun handleUserSelectGroupType(type: ServerGroupType) {
        setDefaultGroupType(type = type.id)
    }

    override fun onCleared() {
        super.onCleared()
        getServerListJob?.cancel()
        getDefaultGroupTypeJob?.cancel()
    }

    data class ViewState(
        val groupTypes: List<ServerGroupType> = listOf(),
        val selectedGroupType: ServerGroupType? = null,
    ) : BaseViewState

    sealed interface ViewEvent : BaseViewEvent {
        data class SelectServerGroupType(val type: ServerGroupType) : ViewEvent
    }

    sealed interface ViewEffect : BaseViewEffect
}
