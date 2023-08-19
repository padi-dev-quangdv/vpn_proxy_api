package com.midterm.securevpnproxy.presentation.main.home

import android.app.Activity
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle.State
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentHomeBinding
import com.midterm.securevpnproxy.util.extensions.observe
import com.midterm.securevpnproxy.vpn_state.DnsVpnManager
import com.tanify.library.dns.domain.model.server_list.ServerGroupType
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(layoutId = R.layout.fragment_home) {

    @Inject
    lateinit var dnsVpnManager: DnsVpnManager

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

    override fun initViewListener() {
        binding.imageTurnOn.setOnClickListener(this)
        binding.layoutHeader.iconRight.setOnClickListener(this)
        binding.btnFilter.setOnClickListener(this)
        binding.btnNavigateToPremium.setOnClickListener(this)
    }

    override fun onViewClicked(view: View) {
        super.onViewClicked(view)
        when (view.id) {
            binding.imageTurnOn.id -> toggleOnOff()
            binding.layoutHeader.iconRight.id -> goToProfile()
            binding.btnFilter.id -> goToFilter()
            binding.btnNavigateToPremium.id -> goToPremium()
        }
    }

    override fun initData() {
    }

    override fun initObserver() {
        observe(viewModel.state, State.STARTED) {
            handleOnOffState(it.onOffState)
            handleGroupTypeChanges(it.currentGroupType)
        }
        observe(viewModel.effect, State.RESUMED) {
            handleEffect(it)
        }
    }

    private fun handleGroupTypeChanges(currentGroupType: ServerGroupType?) {
        binding.tvSubTitleFilter.text = currentGroupType?.displayName
        binding.subDescriptionFilter.text = getString(R.string.filter_protected)
    }

    private fun handleEffect(effect: HomeViewModel.ViewEffect) {
        when (effect) {
            is HomeViewModel.ViewEffect.TurnVpn -> turnVpn(effect.on)
        }
    }

    private fun turnVpn(on: Boolean) {
        if (on) {
            if (!dnsVpnManager.isPermissionGranted()) {
                dnsPrepareResult.launch(dnsVpnManager.getVpnPrepareIntent())
            } else {
                dnsVpnManager.start()
            }
        } else {
            dnsVpnManager.stop()
        }
    }

    private fun handleOnOffState(isOn: Boolean) {
        if (isOn) {
            binding.imageTurnOn.setImageResource(R.drawable.ic_connected)

            binding.tvStatus.setTextColor(
                ContextCompat.getColor(binding.tvStatus.context, R.color.primary_main)
            )
            binding.tvStatus.setText(R.string.protected_status)
            binding.ivBackgroundSuccess.isVisible = true
        } else {
            binding.imageTurnOn.setImageResource(R.drawable.ic_disconnected)
            binding.tvStatus.setTextColor(
                ContextCompat.getColor(binding.tvStatus.context, R.color.danger_main)
            )
            binding.tvStatus.setText(R.string.not_protected_status)
            binding.ivBackgroundSuccess.isVisible = false
        }
    }

    override fun initView() {
    }
}