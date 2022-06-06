package com.example.airbnb.common

import androidx.databinding.BindingAdapter

@BindingAdapter("app:bodyTextBinding")
fun setBodyText(view: CustomAppBar, text: String?) {
    if (text != null) {
        view.setBodyText(text)
    } else {
        view.setBodyText("1,000 - 1,000,000+")
    }
}