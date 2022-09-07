package com.midterm.securevpnproxy.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.midterm.securevpnproxy.databinding.FragmentSeverListBinding

class SeverListFragment : Fragment() {

    private var _binding: FragmentSeverListBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: SeverListFragmentArgs by navArgs()
    private lateinit var filter: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeverListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filter = navigationArgs.filter

        binding.apply {
            when (filter) {
                tvSecurityFilter.text.toString() -> rdSecurityFilter.isChecked = true
                tvAdultFilter.text.toString() -> rdAdultFilter.isChecked = true
                tvFamilyFilter.text.toString() -> rdFamilyFilter.isChecked = true
            }
            rdSecurityFilter.setOnClickListener {
                onRadioButtonClickToNavigateToHome(
                    rdSecurityFilter,
                    rdAdultFilter,
                    rdFamilyFilter,
                    tvSecurityFilter.text.toString()
                )
            }
            rdAdultFilter.setOnClickListener {
                onRadioButtonClickToNavigateToHome(
                    rdAdultFilter,
                    rdSecurityFilter,
                    rdFamilyFilter,
                    tvAdultFilter.text.toString())
            }
            rdFamilyFilter.setOnClickListener {
                onRadioButtonClickToNavigateToHome(
                    rdFamilyFilter,
                    rdAdultFilter,
                    rdSecurityFilter,
                    tvFamilyFilter.text.toString())
            }
            imageProfile.setOnClickListener {
                val action = SeverListFragmentDirections.actionSeverListFragmentToProfileFragment()
                findNavController().navigate(action)
            }
        }
    }

    private fun onRadioButtonClickToNavigateToHome(
        rdSelected: RadioButton,
        rd1: RadioButton,
        rd2: RadioButton,
        filter: String
    ) {
        rdSelected.isChecked = true
        rd1.isChecked = false
        rd2.isChecked = false
        val action = SeverListFragmentDirections.actionSeverListFragmentToHomeFragment(filter)
        findNavController().navigate(action)
    }
}