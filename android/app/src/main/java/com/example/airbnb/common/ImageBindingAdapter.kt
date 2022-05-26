package com.example.airbnb.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.airbnb.di.NetworkModule

@BindingAdapter("image")
fun setImage(view: ImageView, imageUrl: String) {
    view.load(imageUrl) {
        transformations(RoundedCornersTransformation(5f, 5f, 5f, 5f))
    }
}
