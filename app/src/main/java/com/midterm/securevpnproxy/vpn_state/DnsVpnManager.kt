package com.midterm.securevpnproxy.vpn_state

import android.content.Intent
import kotlinx.coroutines.flow.Flow

interface DnsVpnManager {

    fun start()

    fun stop()

    fun getState(): Flow<DnsVpnRunningState>

    suspend fun updateState(state: DnsVpnRunningState)

    fun isPermissionGranted(): Boolean

    fun getVpnPrepareIntent(): Intent?
}