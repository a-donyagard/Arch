package com.arash.arch.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Handle loading more items in [RecyclerView], if scroll to end of list
 */
class LoadMoreScrollListener(private val onLoadMore: () -> Unit) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val recyclerViewItemCount = recyclerView.adapter!!.itemCount
        if (recyclerViewItemCount > 0 &&
            layoutManager.findLastVisibleItemPosition() >= recyclerViewItemCount - 2
        ) {
            onLoadMore()
        }
    }
}