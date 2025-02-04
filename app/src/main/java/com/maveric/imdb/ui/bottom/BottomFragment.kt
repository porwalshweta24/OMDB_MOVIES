package com.maveric.imdb.ui.bottom

import android.os.Bundle
import com.maveric.imdb.NavGraphMainDirections.Companion.actionGlobalMovies
import com.maveric.imdb.NavGraphMainDirections.Companion.actionGlobalNews
import com.maveric.imdb.R
import com.maveric.imdb.databinding.FragmentBottomBinding
import com.maveric.imdb.ui.base.MvvmFragment
import kotlinx.android.synthetic.main.fragment_bottom.*

class BottomFragment : MvvmFragment<FragmentBottomBinding, BottomViewModel>(
    R.layout.fragment_bottom,
    BottomViewModel::class.java
) {
    override fun onEveryInitialization(savedBundle: Bundle?) {
        actionBar?.apply {
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(false)
        }

        bottomNavigationView?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bottom_movies ->
                    bottomNavigation?.navigate(actionGlobalMovies())

                R.id.menu_bottom_news ->
                    bottomNavigation?.navigate(actionGlobalNews())
            }

            true
        }
    }

    override fun onBackPressed(): Boolean = when (mainNavigation?.currentDestination?.id) {
        R.id.destinationMovies -> {
            activity?.finish()
            true
        }

        else -> {
            bottomNavigation?.navigate(actionGlobalMovies())
            bottomNavigationView?.selectedItemId = R.id.menu_bottom_movies
            true
        }
    }
}