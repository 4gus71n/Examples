package com.kimboo.example3.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginatedScrollListener(
    val pageOffset: Int = 4
) : RecyclerView.OnScrollListener() {
    var isLoading: Boolean = false
    var isLastPage: Boolean = false
    var currentPage: Int = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        if (!isLoading && !isLastPage) {
            if (layoutManager.findLastCompletelyVisibleItemPosition() >=
                layoutManager.itemCount - pageOffset) {
                onLoadNextPage(++currentPage)
            }
        }
    }

    abstract fun onLoadNextPage(page: Int)
}