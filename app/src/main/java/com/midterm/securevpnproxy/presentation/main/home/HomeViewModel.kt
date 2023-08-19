package com.midterm.securevpnproxy.presentation.main.home

import androidx.lifecycle.MutableLiveData
import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.presentation.main.home.HomeViewModel.*
import com.midterm.securevpnproxy.vpn_state.DnsVpnManager
import com.midterm.securevpnproxy.vpn_state.DnsVpnRunningState
import com.tanify.library.dns.domain.model.server_list.ServerGroupType
import com.tanify.library.localdb.tanify.UserDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
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
                val type =
                    ServerGroupType.values().find { it.id == typeId }
                setState(currentState.copy(currentGroupType = type))
            }
            .launchIn(coroutineScope)
    }

    private fun subscribeUserVpnOnOffState() {
        userVpnOnOffStateJob?.cancel()
        userVpnOnOffStateJob = userDataStore.isVpnTurnedOn()
            .onEach { isOn ->
                setState(currentState.copy(onOffState = isOn))
                setEffect(ViewEffect.TurnVpn(isOn))
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
            DnsVpnRunningState.Starting, DnsVpnRunningState.Started -> true
            DnsVpnRunningState.Stopping, DnsVpnRunningState.Stopped -> false
        }
        setState(currentState.copy(onOffState = uiStateOn))
    }

    override fun onEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.OnOffToggle -> handleToggleOnOff()
        }
    }

    private fun handleToggleOnOff() {
        val newOnOffState = !currentState.onOffState
        coroutineScope.launch {
            userDataStore.saveVpnTurned(newOnOffState)
        }
    }

    override fun onCleared() {
        super.onCleared()
        dnsVpnStateJob?.cancel()
        userVpnOnOffStateJob?.cancel()
    }

    data class ViewState(
        val onOffState: Boolean = false,
        val currentGroupType: ServerGroupType? = null
    ) : BaseViewState


    sealed interface ViewEvent : BaseViewEvent {
        object OnOffToggle : ViewEvent
    }

    sealed interface ViewEffect : BaseViewEffect {
        data class TurnVpn(val on: Boolean) : ViewEffect
    }
}