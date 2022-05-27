package com.example.airbnb.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.airbnb.R

@BindingAdapter("minute")
fun setTextView(view: TextView, text: Int) {
    val subtitle = view.context.getString(R.string.from_time)
    val textBuilder = StringBuilder(text.toString())
    textBuilder.append(subtitle)

    view.text = textBuilder
}