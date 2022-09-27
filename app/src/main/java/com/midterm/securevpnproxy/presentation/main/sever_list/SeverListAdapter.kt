package com.midterm.securevpnproxy.presentation.main.sever_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.midterm.securevpnproxy.R

class SeverListAdapter(
    private val list: List<ItemSeverListData>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<SeverListAdapter.ItemViewHolder>() {

    private var currentPosition = -1

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemSeverListData: ItemSeverListData) {
            val radioButtonFilter = itemView.findViewById<RadioButton>(R.id.radioButton)
            val tvTitle = itemView.findViewById<TextView>(R.id.tvItemTitle)

            radioButtonFilter.isChecked = currentPosition == adapterPosition
            tvTitle.text = itemSeverListData.name

            radioButtonFilter.setOnCheckedChangeListener { compoundButton, b ->
                if(b) {
                    currentPosition = adapterPosition
                    itemClickListener.onClick()
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SeverListAdapter.ItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sever_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeverListAdapter.ItemViewHolder, position: Int) {
        val currentItem = list[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}