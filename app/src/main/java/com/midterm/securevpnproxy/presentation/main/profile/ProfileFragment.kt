package com.midterm.securevpnproxy.presentation.main.profile

import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentProfileBinding
import com.midterm.securevpnproxy.presentation.ui_model.UiUserDataModel
import com.midterm.securevpnproxy.presentation.ui_model.UiUserStatus
import com.midterm.securevpnproxy.util.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FragmentProfileBinding, ProfileViewModel>(layoutId = R.layout.fragment_profile) {

    override fun initData() {
    }

    override fun initViewListener() {
        binding.apply {
            btnAccount.setOnClickListener {
                val action =
                    ProfileFragmentDirections.actionProfileFragmentToAccountInformationFragment()
                findNavController().navigate(action)
            }
            btnSetting.setOnClickListener {
                val action = ProfileFragmentDirections.actionProfileFragmentToSettingsFragment()
                findNavController().navigate(action)
            }
            layoutHeader.iconLeft.setOnClickListener {
                val action = ProfileFragmentDirections.actionProfileFragmentToHomeFragment()
                findNavController().navigate(action)
            }
            btnAbout.setOnClickListener {
                val action = ProfileFragmentDirections.actionProfileFragmentToAboutFragment()
                findNavController().navigate(action)
            }
            btnLogout.setOnClickListener {
                val sharedPreferences =
                    activity?.getSharedPreferences("sharedPrefs", 0)
                val editor = sharedPreferences?.edit()
                editor?.putString("name", "false")
                editor?.apply()
                activity?.finish()
            }
        }
    }

    override fun initObserver() {
        observe(viewModel.state) { handleState(it) }
    }

    private fun handleState(state: ProfileViewModel.ViewState) {
        updateUserInformation(state.userModel)
    }

    private fun updateUserInformation(userModel: UiUserDataModel?) {
        userModel ?: return
        binding.tvTitle.text = getString(R.string.title_profile, userModel.fullName)
        binding.tvSubscriptionStatus.setText(userModel.status.displayText)
        handlePremiumAdsSection(userModel.status)
    }

    private fun handlePremiumAdsSection(status: UiUserStatus) {
        val shouldShowAds = status != UiUserStatus.Premium
        binding.layoutProfilePremium.root.isVisible = shouldShowAds
    }

    override fun initView() {
    }
}