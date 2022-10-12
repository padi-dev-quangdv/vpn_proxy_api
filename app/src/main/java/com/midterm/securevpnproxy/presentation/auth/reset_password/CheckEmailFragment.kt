package com.midterm.securevpnproxy.presentation.auth.reset_password

import android.view.View
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentCheckEmailBinding

class CheckEmailFragment :
    BaseFragment<FragmentCheckEmailBinding, CheckEmailViewModel>(layoutId = R.layout.fragment_check_email) {

    override fun initData() {
    }

    private fun gotoLogin() {
        val action = CheckEmailFragmentDirections.actionCheckEmailFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    override fun onViewClicked(view: View) {
        super.onViewClicked(view)
        when (view.id) {
            binding.btnBack.id -> gotoLogin()
        }
    }

    override fun initViewListener() {
        binding.btnBack.setOnClickListener(this)
    }

    override fun initObserver() {
    }

    override fun initView() {
    }
}