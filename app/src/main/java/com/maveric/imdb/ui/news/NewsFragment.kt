package com.maveric.imdb.ui.news

import android.os.Bundle
import android.view.*
import com.maveric.imdb.R
import com.maveric.imdb.databinding.FragmentNewsBinding
import com.maveric.imdb.ui.base.MvvmFragment

class NewsFragment : MvvmFragment<FragmentNewsBinding, NewsViewModel>(
    R.layout.fragment_news,
    NewsViewModel::class.java
) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = super.onCreateView(inflater, container, savedInstanceState)?.apply {
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onEveryInitialization(savedBundle: Bundle?) {
        actionBar?.setTitle(R.string.main_menu_news)
    }
}