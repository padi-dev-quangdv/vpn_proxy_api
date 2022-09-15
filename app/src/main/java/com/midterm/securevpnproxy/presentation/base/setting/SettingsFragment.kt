package com.midterm.securevpnproxy.presentation.base.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            imageBack.setOnClickListener {
                val action = SettingsFragmentDirections.actionSettingsFragmentToProfileFragment()
                findNavController().navigate(action)
            }
            btnWhiteListApps.setOnClickListener {
                val action = SettingsFragmentDirections.actionSettingsFragmentToWhiteListAppsFragment()
                findNavController().navigate(action)
            }
        }
    }

}