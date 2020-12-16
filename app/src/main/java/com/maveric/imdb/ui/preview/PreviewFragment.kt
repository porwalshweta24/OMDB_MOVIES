package com.maveric.imdb.ui.preview

import androidx.navigation.fragment.navArgs
import com.maveric.imdb.R
import com.maveric.imdb.databinding.FragmentPreviewBinding
import com.maveric.imdb.ui.base.MvvmFragment

class PreviewFragment : MvvmFragment<FragmentPreviewBinding, PreviewViewModel>(
    R.layout.fragment_preview,
    PreviewViewModel::class.java
) {
    private val args by navArgs<PreviewFragmentArgs>()

    override fun onFirstInitialization() {
        data.imageUri.value = args.imageUri
    }
}