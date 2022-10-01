package com.midterm.securevpnproxy.presentation.main.sever_list

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.midterm.securevpnproxy.R
import com.midterm.securevpnproxy.base.BaseFragment
import com.midterm.securevpnproxy.databinding.FragmentSeverListBinding

class SeverListFragment :
    BaseFragment<FragmentSeverListBinding, SeverListViewModel>(layoutId = R.layout.fragment_sever_list) {

    private val navigationArgs: SeverListFragmentArgs by navArgs()
    private lateinit var filter: String
    private lateinit var adapter: SeverListAdapter
    private lateinit var itemClickListener: ItemClickListener

    override fun initData() {
        filter = navigationArgs.filter

        itemClickListener = object : ItemClickListener {
            override fun onClick() {
                binding.rcvFilter.post(Runnable {
                    adapter.notifyDataSetChanged()
                })
            }
        }
        adapter = SeverListAdapter( itemClickListener,0)
        binding.rcvFilter.adapter = adapter
        viewModel.allSeverDataList.observe(viewLifecycleOwner) { list ->
            list.let {
                adapter.submitList(list)
            }
       }
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