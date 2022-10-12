package com.midterm.securevpnproxy.presentation.auth.splash

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentOnboardingBinding

class OnBoardingFragment : BaseFragment<FragmentOnboardingBinding, OnBoardingViewModel>(layoutId = R.layout.fragment_onboarding){

    private lateinit var adapter: ViewPagerAdapter
    private lateinit var onBoardingDataList: List<OnBoardingData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(viewModel.restorePrefData(this@OnBoardingFragment)) {
            val action = OnBoardingFragmentDirections.actionOnboardingFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }

    private fun setButtonText(position: Int, btnContinue: Button) {
        when(position) {
            0,1 -> btnContinue.setText(R.string.btn_continue_text)
            2 -> btnContinue.setText(R.string.btn_get_started_text)
            else -> btnContinue.setText(R.string.btn_get_started_text)
        }
    }

    override fun initData() {
        onBoardingDataList = listOf(
            OnBoardingData(
                R.drawable.ic_product_launch,
                resources.getString(R.string.title_on_boarding_1),
                resources.getString(R.string.description_on_boarding_1)
            ),
            OnBoardingData(
                R.drawable.ic_coding,
                resources.getString(R.string.title_on_boarding_2),
                resources.getString(R.string.description_on_boarding_2)
            ),
            OnBoardingData(
                R.drawable.ic_location,
                resources.getString(R.string.title_on_boarding_3),
                resources.getString(R.string.description_on_boarding_3)
            )
        )
        adapter = ViewPagerAdapter(onBoardingDataList)
        binding.viewPagerOnBoarding.adapter = adapter
        binding.indicator.setViewPager(binding.viewPagerOnBoarding)
    }

    private fun skipToLogin() {
        val action = OnBoardingFragmentDirections.actionOnboardingFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun gotoLogin() {
        binding.apply {
            var position = viewPagerOnBoarding.currentItem
            setButtonText(position,btnContinue)
            if (position < onBoardingDataList.size) {
                position++
                viewPagerOnBoarding.currentItem = position
                setButtonText(position,btnContinue)
            }
            if(position == onBoardingDataList.size) {
                viewModel.savePrefData(this@OnBoardingFragment)
                setButtonText(position,btnContinue)
                val action = OnBoardingFragmentDirections.actionOnboardingFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onViewClicked(view: View) {
        super.onViewClicked(view)
        when (view.id) {
            binding.tvSkip.id -> skipToLogin()
            binding.btnContinue.id -> gotoLogin()
        }
    }

    override fun initViewListener() {
        binding.tvSkip.setOnClickListener(this)
        binding.btnContinue.setOnClickListener(this)
    }

    override fun initObserver() {
    }

    override fun initView() {

    }

}
