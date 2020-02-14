package com.gabcode.search_meli.ui.search

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabcode.core.extension.inflate
import com.gabcode.search_meli.R
import com.gabcode.search_meli.ui.util.ItemListener
import kotlinx.android.synthetic.main.item_search_recent.view.*

class SearchRecentAdapter(
    private val recentList: List<String> = listOf(),
    private val listener: ItemListener<String>
) : RecyclerView.Adapter<SearchRecentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_search_recent)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(recentList[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(value: String, listener: ItemListener<String> ) {
            itemView.run {
                titleRecentTx.text = value
                setOnClickListener { listener.onItemClick(value) }
            }
        }
    }
}