package com.midterm.securevpnproxy.presentation.main.profile

import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentProfileBinding

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
                val action = ProfileFragmentDirections.actionProfileFragmentToHomeFragment(null)
                findNavController().navigate(action)
            }
            btnAbout.setOnClickListener {
                val action = ProfileFragmentDirections.actionProfileFragmentToAboutFragment()
                findNavController().navigate(action)
            }
            btnLogout.setOnClickListener {
                this@ProfileFragment.activity?.finish()
            }
        }
    }

    override fun initObserver() {
    }

    override fun initView() {
    }
}