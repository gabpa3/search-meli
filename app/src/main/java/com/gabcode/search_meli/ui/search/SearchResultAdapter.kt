package com.gabcode.search_meli.ui.search

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.gabcode.core.domain.model.Item
import com.gabcode.core.extension.inflate
import com.gabcode.search_meli.R
import com.gabcode.search_meli.ui.util.ItemListener
import kotlinx.android.synthetic.main.item_search_result.view.*

class SearchResultAdapter(
    private val items: MutableList<Item> = mutableListOf(),
    private val listener: ItemListener<Item>
) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_search_result)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item, listener: ItemListener<Item>) {
            itemView.apply {
                itemPictureImg.load(item.thumbnail)
                itemTitleTx.text = item.title
                itemPriceTx.text = item.price.toString()
                setOnClickListener{listener.onItemClick(item)}
            }
        }
    }
}