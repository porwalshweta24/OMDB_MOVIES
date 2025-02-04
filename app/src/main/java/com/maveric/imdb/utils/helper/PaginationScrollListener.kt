package com.maveric.imdb.utils.helper

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener constructor(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {
    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(view, dx, dy)

        val itemCount = layoutManager.childCount
        val position = layoutManager.itemCount - COUNT_OF_BOTTOM_VISIBLE
        val visiblePosition = layoutManager.findFirstVisibleItemPosition()

        if (isLoading() || isLastPage()) return
        if (itemCount + visiblePosition >= position && visiblePosition >= 0) onListBottom()
    }

    protected abstract fun onListBottom()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    companion object {
        private const val COUNT_OF_BOTTOM_VISIBLE = 3
    }
}