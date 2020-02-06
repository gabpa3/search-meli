package com.gabcode.search_meli.ui.detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.gabcode.core.extension.inflate
import com.gabcode.search_meli.R
import kotlinx.android.synthetic.main.item_detail_picture_pager.view.*

class PictureAdapter(
    private val images: List<String> = listOf()
) : RecyclerView.Adapter<PagerVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        val view = parent.inflate(R.layout.item_detail_picture_pager)
        return PagerVH(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        holder.itemView.run {
            picture.load(images[position]) {
                crossfade(true)
            }
        }
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)