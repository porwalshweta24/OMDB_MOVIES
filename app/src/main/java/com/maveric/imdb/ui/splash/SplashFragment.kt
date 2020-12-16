package com.maveric.imdb.ui.splash

import android.os.Bundle
import com.maveric.imdb.R
import com.maveric.imdb.databinding.FragmentSplashBinding
import com.maveric.imdb.ui.base.MvvmFragment
import com.maveric.imdb.ui.splash.SplashFragmentDirections.Companion.actionSplashToMain
import com.maveric.imdb.utils.field.NonNullObserver

class SplashFragment : MvvmFragment<FragmentSplashBinding, SplashViewModel>(
    R.layout.fragment_splash,
    SplashViewModel::class.java
) {
    override fun onEveryInitialization(savedBundle: Bundle?) {
        data.eventInitialized.observeEvent(viewLifecycleOwner, NonNullObserver {
            applicationNavigation?.navigate(actionSplashToMain())
        })
    }

    override fun onFirstInitialization() {
        data.initializeAll()
    }
}
