package com.gabcode.search_meli.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class EndlessPagingScrollListener(
    private val layoutManager: StaggeredGridLayoutManager
) : RecyclerView.OnScrollListener() {

    private val loadMoreRunnable = Runnable { loadMoreItems() }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (isLoading()) return

        val visibleItemCount = layoutManager.childCount
        val totalItemCount =  layoutManager.itemCount
//        val firstVisibleItemPosition = layoutManager.

//        if (!isLoading && !isLastPage) {
//        }
    }

    abstract fun loadMoreItems()

    abstract fun isLoading(): Boolean

}