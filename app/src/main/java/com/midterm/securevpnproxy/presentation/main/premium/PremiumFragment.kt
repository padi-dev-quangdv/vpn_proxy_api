package com.midterm.securevpnproxy.presentation.main.premium

import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentPremiumBinding

class PremiumFragment :
    BaseFragment<FragmentPremiumBinding, PremiumViewModel>(layoutId = R.layout.fragment_premium) {

    override fun initData() {
    }

    override fun initViewListener() {
        binding.apply {
            sbPremiumOptions.setOnCheckedChangeListener { compoundButton, boolean ->
                if (boolean) {
                    tvPremium.text = resources.getString(R.string.annually_premium)
                    tvPrice.text = resources.getString(R.string.annually_price)
                    tvExtensionTime.text = resources.getString(R.string.year)
                } else {
                    tvPremium.text = resources.getString(R.string.monthly_premium)
                    tvPrice.text = resources.getString(R.string.monthly_price)
                    tvExtensionTime.text = resources.getString(R.string.month)
                }
            }
        }
    }

    override fun initObserver() {

    }

    override fun initView() {

    }

}