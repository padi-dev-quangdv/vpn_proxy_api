package com.midterm.securevpnproxy.presentation.main.setting

import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding,SettingsViewModel>(layoutId = R.layout.fragment_settings) {


    override fun initData() {
    }

    override fun initViewListener() {
        binding.apply {
            layoutHeader.iconLeft.setOnClickListener {
                val action = SettingsFragmentDirections.actionSettingsFragmentToProfileFragment()
                findNavController().navigate(action)
            }
            btnWhiteListApps.setOnClickListener {
                val action = SettingsFragmentDirections.actionSettingsFragmentToWhiteListAppsFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun initObserver() {
    }

    override fun initView() {
    }
}