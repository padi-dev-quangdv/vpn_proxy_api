package com.midterm.securevpnproxy.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.net.VpnService
import android.os.ParcelFileDescriptor
import androidx.core.app.NotificationCompat
import com.midterm.securevpnproxy.BuildConfig
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.presentation.SplashActivity
import com.midterm.securevpnproxy.vpn_state.DnsVpnManager
import com.midterm.securevpnproxy.vpn_state.DnsVpnRunningState
import com.tanify.library.dns.domain.model.server_list.ServerGroupType
import com.tanify.library.dns.domain.usecase.server_list.ServerListParam
import com.tanify.library.dns.domain.usecase.server_list.ServerListUseCase
import com.tanify.library.libcore.usecase.ResultModel
import com.tanify.library.localdb.tanify.UserDataStore
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DnsVpnService : VpnService() {

    private var vpnThread: Thread? = null
    private var vpnInterface: ParcelFileDescriptor? = null
    private val notificationManager by lazy {
        getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }
    private val startAppPendingIntent by lazy {
        val intentForStartingAppWhenClicked = Intent(this, SplashActivity::class.java)
        PendingIntent.getActivity(
            this, 0, intentForStartingAppWhenClicked,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
        )
    }

    private val foregroundNotification by lazy {
        initForegroundNotification()
    }

    @Inject
    lateinit var serverListUseCase: ServerListUseCase

    @Inject
    lateinit var userDataStore: UserDataStore

    @Inject
    lateinit var dnsVpnManager: DnsVpnManager

    private val job = SupervisorJob()

    private val coroutineScope = CoroutineScope(Dispatchers.IO + job)
    private var dnsDataJob: Job? = null
    private val dnsAddresses: MutableStateFlow<List<String>> = MutableStateFlow(listOf())
    private var dnsAddressJob: Job? = null
    override fun onCreate() {
        super.onCreate()
        startForegroundNotification()
        observeDnsData()
        observeDnsAddresses()
    }

    private fun initForegroundNotification(): Notification {
        val notificationChannel = NotificationChannel(
            DNS_NOTIFICATION_CHANNEL_ID,
            DNS_NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(notificationChannel)
        val builder = NotificationCompat.Builder(this, DNS_NOTIFICATION_CHANNEL_ID)
            .setWhen(System.currentTimeMillis())
            .setShowWhen(true)
            .setDefaults(NotificationCompat.DEFAULT_LIGHTS)
            .setAutoCancel(false)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setOngoing(true)
            .setContentIntent(startAppPendingIntent)
            .setContentTitle(getString(R.string.vpn_is_running))
            .setContentText(getString(R.string.vpn_running_desc))
        return builder.build()
    }

    private fun observeDnsAddresses() {
        dnsAddressJob?.cancel()
        dnsAddressJob = dnsAddresses.onEach {
            restartVpn()
        }.launchIn(coroutineScope)
    }

    private fun restartVpn() {
        coroutineScope.launch {
            if (userDataStore.isVpnTurnedOn().firstOrNull() == true) {
                activateVpn()
            }
        }
    }

    private fun observeDnsData() {
        dnsDataJob?.cancel()
        val selectedGroupTypeFlow = userDataStore.getSelectedGroupType()
        dnsDataJob = selectedGroupTypeFlow.onEach { typeId ->
            val groupType = ServerGroupType.values().firstOrNull { it.id == typeId }
            val serverListParam = ServerListParam(type = groupType, takeRandom = true)
            val serverListResult = serverListUseCase.execute(serverListParam).firstOrNull()
            val addresses: List<String> = if (serverListResult is ResultModel.Success) {
                serverListResult.result.mapNotNull { it.ipv4 }
            } else {
                emptyList()
            }
            dnsAddresses.emit(addresses)
        }.launchIn(coroutineScope)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        startForegroundNotification()
        when (intent?.action) {
            ACTION_ACTIVATE -> activateVpn()
            ACTION_DEACTIVATE -> deactivateVpn()
        }
        return START_STICKY
    }

    private fun createVpnThread(): Thread {
        return Thread { initVpnThread() }
    }

    private fun initVpnThread() {
        val dnsAddresses: List<String> = getDnsAddress()
        val whitelistedPackages: List<String> = getWhitelistedPackages()
        val vpnInterfaceBuilder = generateVpnInterface(dnsAddresses, whitelistedPackages)
        vpnInterface = vpnInterfaceBuilder.establish()
        if (vpnInterface != null) {
            updateVpnStatus(DnsVpnRunningState.Started)
//            vpnInterface?.detachFd()
        } else {
            updateVpnStatus(DnsVpnRunningState.Stopping)
            stopSelf()
        }
    }

    private fun generateVpnInterface(
        dnsAddresses: List<String>,
        whitelistedPackages: List<String>
    ): Builder {
        val builder = Builder()
            .setSession(getString(R.string.app_name))
            .setConfigureIntent(startAppPendingIntent)
            .setMtu(1500)
            .addAddress("10.0.2.15", 24)
            .addAddress("10.0.2.16", 24)
            .addAddress("10.0.2.17", 24)
            .addAddress("10.0.2.18", 24)
        dnsAddresses.forEach {
            builder.addDnsServer(it)
        }
        whitelistedPackages.forEach {
            builder.addDisallowedApplication(it)
        }

        return builder
    }

    private fun getWhitelistedPackages(): List<String> {
        return ALWAYS_IGNORE_PACKAGE_NAMES + listOf()
    }

    private fun getDnsAddress(): List<String> {
        return dnsAddresses.value
    }

    private fun startForegroundNotification() {
        startForeground(DNS_NOTIFICATION_FOREGROUND_ID, foregroundNotification)
    }


    private fun deactivateVpn() {
        terminateVpnThread()
        endForegroundNotification()
        stopSelf()
    }

    private fun endForegroundNotification() {
        notificationManager.cancel(DNS_NOTIFICATION_FOREGROUND_ID)
        stopForeground(STOP_FOREGROUND_REMOVE)
    }

    private fun terminateVpnThread() {
        updateVpnStatus(DnsVpnRunningState.Stopping)
        vpnInterface?.close()
        vpnThread?.interrupt()
    }

    private fun activateVpn() {
        terminateVpnThread()
        vpnThread = createVpnThread()
        vpnThread?.start()
        updateVpnStatus(DnsVpnRunningState.Starting)
    }

    override fun onDestroy() {
        super.onDestroy()
        updateVpnStatus(DnsVpnRunningState.Stopped)
        dnsAddressJob?.cancel()
        dnsDataJob?.cancel()
        job.cancel()
    }

    private fun updateVpnStatus(state: DnsVpnRunningState) {
        coroutineScope.launch {
            dnsVpnManager.updateState(state)
        }
    }

    companion object {
        val ACTION_ACTIVATE =
            BuildConfig.APPLICATION_ID + "." + DnsVpnService::class.java.simpleName + "." + "ACTION_ACTIVE"
        val ACTION_DEACTIVATE =
            BuildConfig.APPLICATION_ID + "." + DnsVpnService::class.java.simpleName + "." + "ACTION_DEACTIVATE"
        private val ALWAYS_IGNORE_PACKAGE_NAMES =
            listOf(BuildConfig.APPLICATION_ID, "com.android.vending")
        private const val DNS_NOTIFICATION_CHANNEL_ID = "Dns_notification_channel_id"
        private const val DNS_NOTIFICATION_CHANNEL_NAME = "Blocker VPN"
        private const val DNS_NOTIFICATION_FOREGROUND_ID = 1192
    }
}