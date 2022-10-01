package com.midterm.securevpnproxy.presentation.main.home

import android.os.Bundle
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
        return String.format("%02d", hours) + " : " + String.format(
            "%02d",
            minutes
        ) + " : " + String.format("%02d", seconds)
    }

    override fun initData() {
        filterName = navigationArgs.filter
        binding.apply {
            if (filterName != null && filterName != "") {
                tvSubTitleFilter.text = filterName
            }
        }
    }

    override fun initViewListener() {
        binding.apply {
            layoutHeader.iconRight.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
                findNavController().navigate(action)
            }
            imageTurnOn.setOnClickListener {
                if (tvStatus.text == resources.getString(R.string.not_protected_status)) {
                    imageTurnOn.setImageResource(R.drawable.ic_turn_on_connected)
                    tvStatus.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.success_main, null
                        )
                    )
                    viewModel.timer.value = Timer()
                    startTimer()
                    tvStatus.text = resources.getString(R.string.protected_status)
                } else {
                    imageTurnOn.setImageResource(R.drawable.ic_turn_on_unconnected)
                    tvStatus.setTextColor(
                        ResourcesCompat.getColor(
                            resources,
                            R.color.danger_main, null
                        )
                    )
                    viewModel.timer.value?.cancel()
                    tvTimer.text = formatTime(0, 0, 0)
                    tvStatus.text = resources.getString(R.string.not_protected_status)
                }
            }
            btnFilter.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToSeverListFragment(tvSubTitleFilter.text.toString())
                findNavController().navigate(action)
            }
            btnNavigateToPremium.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToPremiumFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun initObserver() {
    }

    override fun initView() {
    }
}