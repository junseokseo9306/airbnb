package com.example.airbnb.common

import android.util.Log
import androidx.databinding.BindingAdapter

@BindingAdapter("app:bodyTextBinding")
fun setPriceRange(view: CustomAppBar, text: String?) {
    if (text != null) {
        view.setPriceRange(text)
    } else {
        view.setPriceRange("1,000 -")
    }
}

@BindingAdapter("app:clickListener")
fun setClickListener(view: CustomAppBar, listener: CustomViewClick) {
    Log.d("BindingAdapter", if(listener != null) "${listener.toString()}" else "null")
    view.setBackButtonListener(listener)
}