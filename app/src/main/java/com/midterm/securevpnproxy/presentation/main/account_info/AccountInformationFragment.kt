package com.midterm.securevpnproxy.presentation.main.account_info

import android.view.View
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentAccountInformationBinding
import com.midterm.securevpnproxy.presentation.auth.login.LoginFragment


class AccountInformationFragment :
    BaseFragment<FragmentAccountInformationBinding, AccountInformationViewModel>(layoutId = R.layout.fragment_account_information) {

    override fun initData() {
    }

    private fun gotoProfile() {
        val action =
            AccountInformationFragmentDirections.actionAccountInformationFragmentToProfileFragment()
        findNavController().navigate(action)
    }

    private fun backToLogin() {
        val sharedPreferences = activity?.getSharedPreferences(LoginFragment.SHARED_PREFS, 0)
        val editor = sharedPreferences?.edit()
        editor?.putString("name", "")
        editor?.apply()
        activity?.finish()
    }

    override fun initViewListener() {
        binding.layoutHeader.iconLeft.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)
    }

    override fun onViewClicked(v: View) {
        super.onViewClicked(v)
        when (v.id) {
            binding.layoutHeader.iconLeft.id -> gotoProfile()
            binding.btnLogout.id -> backToLogin()
        }
    }

    override fun initObserver() {
    }

    override fun initView() {
    }

}