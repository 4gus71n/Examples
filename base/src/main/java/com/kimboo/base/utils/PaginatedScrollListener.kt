package com.kimboo.base.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginatedScrollListener(
    private val pageOffset: Int = 4
) : RecyclerView.OnScrollListener() {
    var isLoading: Boolean = true
    var isLastPage: Boolean = false
    var currentPage: Int = 1
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        if (!isLoading && !isLastPage) {
            if (layoutManager.findLastCompletelyVisibleItemPosition() >=
                layoutManager.itemCount - pageOffset) {
                isLoading = true
                onLoadNextPage(++currentPage)
            }
        }
    }
    abstract fun onLoadNextPage(page: Int)
}