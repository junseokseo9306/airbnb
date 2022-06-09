package com.example.airbnb.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.airbnb.R

@BindingAdapter("image")
fun setImage(view: ImageView, imageUrl: String) {
    view.load(imageUrl) {
        crossfade(200)
        placeholder(R.drawable.ic_baseline_photo_size_select_actual_24)
        error(R.drawable.img_not_found)
        transformations(RoundedCornersTransformation(5f, 5f, 5f, 5f))
    }
}
