package com.gabcode.search_meli.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class EndlessPagingScrollListener(
    private val layoutManager: StaggeredGridLayoutManager
) : RecyclerView.OnScrollListener() {

    private val loadMoreRunnable = Runnable { loadMore() }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy < 0 || isLoading()) return

        val visibleItemCount = layoutManager.childCount
        val totalItemCount =  layoutManager.itemCount

        val lastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(null)

        val lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)

        if (totalItemCount - visibleItemCount <= lastVisibleItemPosition + ITEMS_TO_SEE) {
            recyclerView.post(loadMoreRunnable)
        }
    }

    abstract fun loadMore()

    abstract fun isLoading(): Boolean

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }

        return maxSize
    }

    companion object {
        private const val ITEMS_TO_SEE = 4
    }

}