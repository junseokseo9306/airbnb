package com.example.airbnb.common

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
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

@BindingAdapter("minPrice")
fun setMinPriceTextView(view: TextView, text: String?) {
    val priceTextBuilder = SpannableStringBuilder("")
    priceTextBuilder.append("최저 요금\n")
    if(text != null) {
        priceTextBuilder.append(text)
        priceTextBuilder.append(",000")
        priceTextBuilder.setSpan(
            AbsoluteSizeSpan(20, true),
            5,
            priceTextBuilder.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        priceTextBuilder.setSpan(
            StyleSpan(Typeface.BOLD),
            5,
            priceTextBuilder.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        view.text = priceTextBuilder
    } else {
        priceTextBuilder.append("1,000")
        priceTextBuilder.setSpan(
            AbsoluteSizeSpan(20, true),
            5,
            priceTextBuilder.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        priceTextBuilder.setSpan(
            StyleSpan(Typeface.BOLD),
            5,
            priceTextBuilder.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        view.text = priceTextBuilder
    }
}

@BindingAdapter("maxPrice")
fun setMaxPriceTextView(view: TextView, text: String?) {
    val priceTextBuilder = SpannableStringBuilder("")
    priceTextBuilder.append("최고 요금\n")
    if(text != null) {
        priceTextBuilder.append(text)
        priceTextBuilder.append(",000")
        priceTextBuilder.setSpan(
            AbsoluteSizeSpan(20, true),
            5,
            priceTextBuilder.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        priceTextBuilder.setSpan(
            StyleSpan(Typeface.BOLD),
            5,
            priceTextBuilder.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        view.text = priceTextBuilder
    } else {
        priceTextBuilder.append("1,000,000")
        priceTextBuilder.setSpan(
            AbsoluteSizeSpan(20, true),
            5,
            priceTextBuilder.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        priceTextBuilder.setSpan(
            StyleSpan(Typeface.BOLD),
            5,
            priceTextBuilder.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        view.text = priceTextBuilder
    }
}