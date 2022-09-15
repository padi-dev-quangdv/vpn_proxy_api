package com.midterm.securevpnproxy.presentation.base.premium

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.databinding.FragmentPremiumBinding


class PremiumFragment : Fragment() {

    private var _binding: FragmentPremiumBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPremiumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            sbPremiumOptions.setOnCheckedChangeListener { compoundButton, boolean ->
                if(boolean) {
                    tvPremium.text = resources.getString(R.string.annually_premium)
                    tvPrice.text = resources.getString(R.string.annually_price)
                    tvExtensionTime.text = resources.getString(R.string.year)
                }
                else {
                    tvPremium.text = resources.getString(R.string.monthly_premium)
                    tvPrice.text = resources.getString(R.string.monthly_price)
                    tvExtensionTime.text = resources.getString(R.string.month)
                }
            }
        }
    }

}