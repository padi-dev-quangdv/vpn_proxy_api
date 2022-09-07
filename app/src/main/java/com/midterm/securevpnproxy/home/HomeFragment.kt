package com.midterm.securevpnproxy.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.databinding.FragmentHomeBinding
import kotlinx.coroutines.*
import java.util.*
import kotlin.math.roundToInt

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: HomeFragmentArgs by navArgs()
    private var filterName: String? = null

    private lateinit var timer: Timer
    private lateinit var timerTask: TimerTask
    private var time = 0.02

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filterName = navigationArgs.filter

        binding.apply {
            imageProfile.setOnClickListener {
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
                    timer = Timer()
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
                    timer.cancel()
                    tvTimer.text = formatTime(0,0,0)
                    tvStatus.text = resources.getString(R.string.not_protected_status)
                }
            }
            btnFilter.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToSeverListFragment(tvSubTitleFilter.text.toString())
                findNavController().navigate(action)
            }
            btnNavigateToPremium.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToPremiumFragment()
                findNavController().navigate(action)
            }
            if(filterName != null && filterName != "") {
                tvSubTitleFilter.text = filterName
            }

        }
    }

    private fun startTimer() {
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
        timer.scheduleAtFixedRate(timerTask,0,1000)
    }

    private fun getTimerText(): String {
        val round = time.roundToInt()
        val seconds: Int = ((round % 86300) % 3600) % 60
        val minutes: Int = ((round % 86300) % 3600) / 60
        val hours: Int = (round % 86300) / 3600

        return formatTime(seconds,minutes,hours)
    }

    private fun formatTime( seconds: Int,  minutes: Int,  hours: Int): String {
        return String.format("%02d",hours) + " : " + String.format("%02d",minutes) + " : " + String.format("%02d",seconds)
    }
}