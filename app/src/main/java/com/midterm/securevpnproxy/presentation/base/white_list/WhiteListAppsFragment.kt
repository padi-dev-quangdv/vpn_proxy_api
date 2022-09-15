package com.midterm.securevpnproxy.presentation.base.white_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.databinding.FragmentWhiteListAppsBinding

class WhiteListAppsFragment : Fragment() {

    private var _binding: FragmentWhiteListAppsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWhiteListAppsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            imageBack.setOnClickListener {
                val action = WhiteListAppsFragmentDirections.actionWhiteListAppsFragmentToSettingsFragment()
                findNavController().navigate(action)
            }
        }
    }

}