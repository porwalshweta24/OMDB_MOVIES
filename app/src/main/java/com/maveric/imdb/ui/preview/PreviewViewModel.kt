package com.maveric.imdb.ui.preview

import com.maveric.imdb.ui.base.BaseViewModel
import com.maveric.imdb.utils.field.NullableField

class PreviewViewModel : BaseViewModel() {
    var imageUri = NullableField<String>(null)
}