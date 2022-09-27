package com.midterm.securevpnproxy.presentation.main.white_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.midterm.securevpnproxy.R

class WhiteListAdapter (private val list : List<WhiteListAppData>
) : RecyclerView.Adapter<WhiteListAdapter.WhiteListViewHolder>() {

    inner class WhiteListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(whiteListAppData: WhiteListAppData) {
            val currentImage = itemView.findViewById<ImageView>(R.id.imageTitle)
            val currentContent = itemView.findViewById<TextView>(R.id.tvContent)

            currentImage.setImageResource(whiteListAppData.imageTitle)
            currentContent.text = whiteListAppData.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhiteListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_white_list_app, parent, false)
        return WhiteListViewHolder(view)
    }

    override fun onBindViewHolder(holder: WhiteListViewHolder, position: Int) {
        val currentItem = list.get(position)
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}