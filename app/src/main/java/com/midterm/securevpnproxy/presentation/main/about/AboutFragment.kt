package com.midterm.securevpnproxy.presentation.main.about

import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentAboutBinding


class AboutFragment :
    BaseFragment<FragmentAboutBinding, AboutViewModel>(layoutId = R.layout.fragment_about) {

    override fun initData() {
    }

    override fun initViewListener() {
        binding.apply {
            layoutHeader.iconLeft.setOnClickListener {
                val action = AboutFragmentDirections.actionAboutFragmentToProfileFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun initObserver() {
    }

    override fun initView() {
    }

}