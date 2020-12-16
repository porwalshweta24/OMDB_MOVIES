package com.maveric.imdb.ui.main

import android.os.Bundle
import com.maveric.imdb.R
import com.maveric.imdb.databinding.FragmentMainBinding
import com.maveric.imdb.ui.base.MvvmFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : MvvmFragment<FragmentMainBinding, MainViewModel>(
    R.layout.fragment_main,
    MainViewModel::class.java
) {
    override fun onEveryInitialization(savedBundle: Bundle?) {
        activity?.setSupportActionBar(mainToolbar)
    }
}