package com.midterm.securevpnproxy.vpn_state

import android.content.Context
import android.content.Intent
import android.net.VpnService
import com.midterm.securevpnproxy.service.DnsVpnService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class DnsVpnManagerImpl @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : DnsVpnManager {

    private val runningState: MutableStateFlow<DnsVpnRunningState> =
        MutableStateFlow(DnsVpnRunningState.Stopped)

    private val serviceIntent by lazy {
        Intent(applicationContext, DnsVpnService::class.java)
    }

    override fun start() {
        if (isPermissionGranted()) {
            serviceIntent.action = DnsVpnService.ACTION_ACTIVATE
            applicationContext.startForegroundService(serviceIntent)
        } else {
            Timber.e("Permission is not granted. Stop VPN.")
        }
    }

    override fun stop() {
        serviceIntent.action = DnsVpnService.ACTION_DEACTIVATE
        applicationContext.startForegroundService(serviceIntent)
    }

    override fun getState(): Flow<DnsVpnRunningState> {
        return flow {
            emitAll(runningState)
        }
    }

    override suspend fun updateState(state: DnsVpnRunningState) {
        runningState.emit(state)
    }

    override fun isPermissionGranted(): Boolean {
        return getVpnPrepareIntent() == null
    }

    override fun getVpnPrepareIntent(): Intent? {
        return VpnService.prepare(applicationContext)
    }
}