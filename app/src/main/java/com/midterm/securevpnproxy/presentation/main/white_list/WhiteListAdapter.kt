package com.midterm.securevpnproxy.presentation.main.white_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.midterm.securevpnproxy.databinding.ItemWhiteListAppBinding

class WhiteListAdapter :
    ListAdapter<WhiteListAppData, WhiteListAdapter.WhiteListViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<WhiteListAppData>() {
            override fun areItemsTheSame(
                oldItem: WhiteListAppData,
                newItem: WhiteListAppData
            ): Boolean {
                return oldItem.content == newItem.content
            }

            override fun areContentsTheSame(
                oldItem: WhiteListAppData,
                newItem: WhiteListAppData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class WhiteListViewHolder(private val binding: ItemWhiteListAppBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(whiteListAppData: WhiteListAppData) {
            binding.apply {
                imageTitle.setImageResource(whiteListAppData.imageTitle)
                tvContent.text = whiteListAppData.content
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhiteListViewHolder {
        return WhiteListViewHolder(
            ItemWhiteListAppBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: WhiteListViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}