package com.midterm.securevpnproxy.presentation.main.home

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentHomeBinding
import kotlinx.coroutines.*
import java.util.*
import kotlin.math.roundToInt

class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(layoutId = R.layout.fragment_home) {

    private val navigationArgs: HomeFragmentArgs by navArgs()
    private var filterName: String? = null

    private lateinit var timerTask: TimerTask
    private var time = 0.0

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)


    private fun startTimer() {
        viewModel.timer.observe(viewLifecycleOwner) {
            time = 0.0
            timerTask = object : TimerTask() {
                override fun run() {
                    uiScope.launch {
                        withContext(Dispatchers.Main) {
                            time++
                            binding.tvTimer.text = getTimerText()
                        }
                    }
                }
            }
            it.scheduleAtFixedRate(timerTask, 0, 1000)
        }
    }

    private fun getTimerText(): String {
        val round = time.roundToInt()
        val seconds: Int = ((round % 86300) % 3600) % 60
        val minutes: Int = ((round % 86300) % 3600) / 60
        val hours: Int = (round % 86300) / 3600

        return formatTime(seconds, minutes, hours)
    }

    private fun formatTime(seconds: Int, minutes: Int, hours: Int): String {
        return String.format("%02d", hours) + " : " +
                String.format("%02d", minutes) + " : " +
                String.format("%02d", seconds)
    }



    private fun toggleOnOff() {
        val event = HomeViewModel.ViewEvent.OnOffToggle
        viewModel.onEvent(event)
    }

    private fun goToProfile() {
        val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
        findNavController().navigate(action)
    }

    private fun goToFilter() {
        val action =
            HomeFragmentDirections.actionHomeFragmentToSeverListFragment(binding.tvSubTitleFilter.text.toString())
        findNavController().navigate(action)
    }

    private fun goToPremium() {
        val action = HomeFragmentDirections.actionHomeFragmentToPremiumFragment()
        findNavController().navigate(action)
    }

    override fun initViewListener() {
        binding.imageTurnOn.setOnClickListener(this)
        binding.layoutHeader.iconRight.setOnClickListener(this)
        binding.btnFilter.setOnClickListener(this)
        binding.btnNavigateToPremium.setOnClickListener(this)
    }

    override fun onViewClicked(v: View) {
        super.onViewClicked(v)
        when (v.id) {
            binding.imageTurnOn.id -> toggleOnOff()
            binding.layoutHeader.iconRight.id -> goToProfile()
            binding.btnFilter.id -> goToFilter()
            binding.btnNavigateToPremium.id -> goToPremium()
        }
    }

    override fun initData() {
        filterName = navigationArgs.filter
        binding.apply {
            if (filterName != null && filterName != "") {
                tvSubTitleFilter.text = filterName
            }
        }
    }

    override fun initObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            handleOnOffState(it.onOffState)
        }
    }

    private fun handleOnOffState(isOn: Boolean) {
        if (isOn) {
            binding.imageTurnOn.setImageResource(R.drawable.ic_connected)

            binding.tvStatus.setTextColor(
                ContextCompat.getColor(binding.tvStatus.context, R.color.success_main)
            )
            binding.tvStatus.setText(R.string.protected_status)
        } else {
            binding.imageTurnOn.setImageResource(R.drawable.ic_disconnected)
            binding.tvStatus.setTextColor(
                ContextCompat.getColor(binding.tvStatus.context, R.color.danger_main)
            )
            binding.tvStatus.setText(R.string.not_protected_status)
        }
    }

    override fun initView() {
    }
}