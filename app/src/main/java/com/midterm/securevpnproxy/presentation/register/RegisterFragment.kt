package com.midterm.securevpnproxy.presentation.register

import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentRegisterBinding
import com.midterm.securevpnproxy.presentation.ViewEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment :
    BaseFragment<FragmentRegisterBinding, RegisterViewModel>(layoutId = R.layout.fragment_register) {

    override fun initData() {
    }

    override fun initViewListener() {
        binding.btnRegister.setOnClickListener {
            val fullName = binding.inputFullName.etInput.text.toString()
            val email = binding.inputEmail.etInput.text.toString()
            val password = binding.inputPassword.etInput.text.toString()
            val confirmPassword = binding.inputConfirmPassword.etInput.text.toString()
            viewModel.onEvent(ViewEvent.RegisterEvent(fullName, email, password,confirmPassword))
//            val intent =
//                Intent(this@RegisterFragment.requireContext(), HomeActivity::class.java)
//            startActivity(intent)
        }
        binding.tvNavigateToLogin.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

    override fun initObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            binding.inputFullName.etInput.error = it.fullNameError
            binding.inputEmail.etInput.error = it.emailError
            binding.inputPassword.etInput.error = it.passwordError
            binding.inputConfirmPassword.etInput.error = it.confirmPasswordError
        }
    }

    override fun initView() {
    }
}