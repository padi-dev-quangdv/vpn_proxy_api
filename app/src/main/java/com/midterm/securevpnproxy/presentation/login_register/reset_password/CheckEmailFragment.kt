package com.midterm.securevpnproxy.presentation.login_register.reset_password

import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentCheckEmailBinding

class CheckEmailFragment :
    BaseFragment<FragmentCheckEmailBinding, CheckEmailViewModel>(layoutId = R.layout.fragment_check_email) {

    override fun initData() {
    }

    override fun initViewListener() {
        binding.btnBack.setOnClickListener {
            val action = CheckEmailFragmentDirections.actionCheckEmailFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

    override fun initObserver() {
    }

    override fun initView() {
    }
}