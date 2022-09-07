package com.midterm.securevpnproxy.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.databinding.FragmentAccountInformationBinding


class AccountInformationFragment : Fragment() {

    private var _binding: FragmentAccountInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            imageBack.setOnClickListener {
                val action = AccountInformationFragmentDirections.actionAccountInformationFragmentToProfileFragment()
                findNavController().navigate(action)
            }
            btnLogout.setOnClickListener {
                this@AccountInformationFragment.activity?.finish()
            }
        }
    }

}