package com.midterm.securevpnproxy.presentation.main.home.home_main

import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.presentation.main.home.HomeViewModel
import com.midterm.securevpnproxy.presentation.main.home.home_main.HomeMainViewModel.*
import com.midterm.securevpnproxy.presentation.main.home.home_main.model.VpnProfileUi
import com.midterm.securevpnproxy.presentation.main.home.home_main.ui.VpnToggleState
import com.midterm.securevpnproxy.vpn_state.DnsVpnManager
import com.midterm.securevpnproxy.vpn_state.DnsVpnRunningState
import com.tanify.library.dns.domain.model.server_list.ServerGroupType
import com.tanify.library.localdb.tanify.UserDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class HomeMainViewModel @Inject constructor(
    private val dnsVpnManager: DnsVpnManager,
    private val userDataStore: UserDataStore,
) : BaseViewModel<ViewState, ViewEvent, ViewEffect>(ViewState()) {

    private var dnsVpnStateJob: Job? = null
    private var userVpnOnOffStateJob: Job? = null
    private var userServerGroupTypeJob: Job? = null

    init {
        subscribeDnsVpnState()
        subscribeUserVpnOnOffState()
        subscribeServerGroupType()
    }

    private fun subscribeServerGroupType() {
        userServerGroupTypeJob?.cancel()
        userServerGroupTypeJob = userDataStore.getSelectedGroupType()
            .onEach { typeId ->
                val type = ServerGroupType.values()
                    .firstOrNull { it.id == typeId } ?: ServerGroupType.Standard
                setState(currentState.copy(currentGroupType = type))
            }
            .launchIn(coroutineScope)
    }

    private fun subscribeUserVpnOnOffState() {
        userVpnOnOffStateJob?.cancel()
        userVpnOnOffStateJob = userDataStore.isVpnTurnedOn()
            .onEach { isOn ->
                val toggleState = if (isOn) {
                    VpnToggleState.Active
                } else {
                    VpnToggleState.Inactive
                }
                setState(currentState.copy(status = toggleState))
                setEffect(ViewEffect.TurnVpn(toggleState))
            }
            .launchIn(coroutineScope)
    }

    private fun subscribeDnsVpnState() {
        dnsVpnStateJob?.cancel()
        dnsVpnStateJob = dnsVpnManager.getState()
            .onEach {
                handleDnsVpnState(it)
            }
            .launchIn(coroutineScope)
    }

    private fun handleDnsVpnState(it: DnsVpnRunningState) {
        val uiStateOn = when (it) {
            DnsVpnRunningState.Starting, DnsVpnRunningState.Started -> VpnToggleState.Active
            DnsVpnRunningState.Stopping, DnsVpnRunningState.Stopped -> VpnToggleState.Inactive
        }
        setState(currentState.copy(status = uiStateOn))
    }

    override fun onEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.OnOffToggle -> handleToggleOnOff()
        }
    }

    private fun handleToggleOnOff() {
        val newToggleState = currentState.status != VpnToggleState.Active
        coroutineScope.launch {
            userDataStore.saveVpnTurned(newToggleState)
        }
    }

    override fun onCleared() {
        super.onCleared()
        dnsVpnStateJob?.cancel()
        userVpnOnOffStateJob?.cancel()
    }

    data class ViewState(
        val status: VpnToggleState = VpnToggleState.Inactive,
        val timer: String = "",
        val currentGroupType: ServerGroupType = ServerGroupType.Standard,
    ) : BaseViewState

    sealed interface ViewEvent : BaseViewEvent {
        object OnOffToggle : ViewEvent
    }

    sealed interface ViewEffect : BaseViewEffect {
        data class TurnVpn(val newState: VpnToggleState) : ViewEffect
    }
}
