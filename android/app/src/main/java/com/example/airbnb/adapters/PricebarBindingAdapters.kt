package com.example.airbnb.adapters

import androidx.databinding.BindingAdapter
import com.example.airbnb.ui.custom.CustomAppBar

@BindingAdapter("app:bodyTextBinding")
fun setPriceMinRange(view: CustomAppBar, text: String?) {
    if (text != null) {
        view.setPriceMinRange(text)
    } else {
        view.setPriceMinRange("1,000 -")
    }
}

@BindingAdapter("app:countTextBinding")
fun setCountsRange(view: CustomAppBar, text: String?) {
    if (text != null) {
        view.setPriceMinRange(text)
    } else {
        view.setPriceMinRange("게스트 0, 유아 0")
    }
}