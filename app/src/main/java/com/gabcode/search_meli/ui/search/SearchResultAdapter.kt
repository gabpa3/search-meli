package com.gabcode.search_meli.ui.search

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabcode.core.domain.model.Item
import com.gabcode.core.extension.inflate
import com.gabcode.search_meli.R
import com.gabcode.search_meli.ui.util.ImageUtil
import com.gabcode.search_meli.ui.util.ItemListener
import com.gabcode.search_meli.ui.util.formatDecimal
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

    fun addItems(newItems: List<Item>) {
        val currentSize = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(currentSize, newItems.size)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item, listener: ItemListener<Item>) {
            val context = itemView.context
            itemView.run {
                ImageUtil.loadFull(itemPictureImg, item.thumbnail)
                itemTitleTx.text = item.title
                itemPriceTx.text = String.format(context.getString(R.string.price_format),
                    item.currency, formatDecimal(item.price))
                setOnClickListener{listener.onItemClick(item)}
            }
        }
    }
}