package com.midterm.securevpnproxy.presentation.main.sever_list

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentSeverListBinding


class SeverListFragment :
    BaseFragment<FragmentSeverListBinding, SeverListViewModel>(layoutId = R.layout.fragment_sever_list) {

    private val navigationArgs: SeverListFragmentArgs by navArgs()
    private lateinit var filter: String
    private lateinit var itemListSeverList: List<ItemSeverListData>
    private lateinit var adapter: SeverListAdapter
    private lateinit var itemClickListener: ItemClickListener

    override fun initData() {
        filter = navigationArgs.filter

        itemListSeverList = listOf(
            ItemSeverListData(isChecked = false, name = getString(R.string.security_filter)),
            ItemSeverListData(isChecked = false, name = getString(R.string.adult_filter)),
            ItemSeverListData(isChecked = false, name = getString(R.string.family_filter))
        )
        itemClickListener = object : ItemClickListener {
            override fun onClick() {
                binding.rcvFilter.post(Runnable {
                    adapter.notifyDataSetChanged()
                })
            }
        }
        adapter = SeverListAdapter(itemListSeverList, itemClickListener)
        binding.rcvFilter.adapter = adapter
        val manager = GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL, false)
        binding.rcvFilter.layoutManager = manager


    }

    override fun initViewListener() {
        binding.layoutHeader.iconRight.setOnClickListener {
            val action = SeverListFragmentDirections.actionSeverListFragmentToProfileFragment()
            findNavController().navigate(action)
        }
    }

    override fun initObserver() {
    }

    override fun initView() {

    }
}