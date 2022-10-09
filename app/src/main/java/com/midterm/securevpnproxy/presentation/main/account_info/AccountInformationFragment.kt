package com.midterm.securevpnproxy.presentation.main.account_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentAccountInformationBinding


class AccountInformationFragment :
    BaseFragment<FragmentAccountInformationBinding, AccountInformationViewModel>(layoutId = R.layout.fragment_account_information) {

    override fun initData() {
    }

    override fun initViewListener() {
        binding.apply {
            layoutHeader.iconLeft.setOnClickListener {
                val action =
                    AccountInformationFragmentDirections.actionAccountInformationFragmentToProfileFragment()
                findNavController().navigate(action)
            }
            btnLogout.setOnClickListener {
                this@AccountInformationFragment.activity?.finish()
            }
        }
    }

    override fun initObserver() {
    }

    override fun initView() {
    }

}