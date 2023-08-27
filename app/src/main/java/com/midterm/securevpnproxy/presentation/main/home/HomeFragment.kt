package com.midterm.securevpnproxy.presentation.main.home

import android.app.Activity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseComposeFragment
import com.midterm.securevpnproxy.databinding.LayoutComposeOnlyBinding
import com.midterm.securevpnproxy.presentation.main.home.ui.HomeHeader
import com.midterm.securevpnproxy.vpn_state.DnsVpnManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.midterm.securevpnproxy.presentation.main.home.home_main.HomeMainScreen
import com.midterm.securevpnproxy.presentation.main.home.home_main.ui.VpnToggleState
import com.midterm.securevpnproxy.presentation.main.home.home_select_mode.HomeSelectModeScreen
import com.midterm.securevpnproxy.presentation.main.home.model.HomeChildScreen

@AndroidEntryPoint
class HomeFragment :
    BaseComposeFragment<LayoutComposeOnlyBinding, HomeViewModel>(layoutId2 = R.layout.layout_compose_only) {
    @Inject
    lateinit var dnsVpnManager: DnsVpnManager

    override fun getMainComposeView(): ComposeView = binding.composeView

    @Composable
    override fun MainComposeViewContent(modifier: Modifier) {
        super.MainComposeViewContent(modifier)
        val viewState by viewModel.state.collectAsStateWithLifecycle(
            initialValue = HomeViewModel.ViewState()
        )
        Column(modifier = modifier) {
            HomeHeader(
                modifier = Modifier.padding(horizontal = 24.dp),
                onStartItemClicked = {

                },
                onEndItemClicked = {

                },
                iconStart = R.drawable.ic_menu,
                iconEnd = R.drawable.ic_person,
            )
            HomeContent(viewState.currentScreen)
        }
    }

    @Composable
    private fun HomeContent(currentScreen: HomeChildScreen) {
        when (currentScreen) {
            HomeChildScreen.Main -> HomeMainScreen(
                onChangeProfileClicked = {

                },
                onSwitchVpnState = { turnVpn(it) }
            )

            HomeChildScreen.SelectMode -> HomeSelectModeScreen()
        }
    }


    private val dnsPrepareResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                dnsVpnManager.start()
            } else {
                toggleOnOff()
            }
        }

    private fun toggleOnOff() {
        val event = HomeViewModel.ViewEvent.OnOffToggle
        viewModel.onEvent(event)
    }

    private fun goToProfile() {
        val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
        findNavController().navigate(action)
    }

    private fun goToFilter() {
        val action =
            HomeFragmentDirections.actionHomeFragmentToSeverListFragment()
        findNavController().navigate(action)
    }

    private fun goToPremium() {
        val action = HomeFragmentDirections.actionHomeFragmentToPremiumFragment()
        findNavController().navigate(action)
    }

    override fun initData() {
    }

    override fun initObserver() {
    }

    private fun turnVpn(state: VpnToggleState) {
        val shouldOn = state == VpnToggleState.Active
        if (shouldOn) {
            if (!dnsVpnManager.isPermissionGranted()) {
                dnsPrepareResult.launch(dnsVpnManager.getVpnPrepareIntent())
            } else {
                dnsVpnManager.start()
            }
        } else {
            dnsVpnManager.stop()
        }
    }

    override fun initView() {
    }
}