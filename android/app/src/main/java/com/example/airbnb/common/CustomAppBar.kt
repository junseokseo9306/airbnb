package com.example.airbnb.common

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.airbnb.R
import com.example.airbnb.databinding.CustomAppbarBinding

class CustomAppBar(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {

    private lateinit var binding: CustomAppbarBinding

    init {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.custom_appbar,
            this,
            true
        )
        getAttrs(attrs)
    }

    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomAppBar)

        typedArray.getString(R.styleable.CustomAppBar_titleText)?.let { setTitleText(it) }

        typedArray.recycle()
    }

    private fun setTitleText(titleText: String) {
        binding.tvPriceRange.text = titleText
    }

    fun setPriceMinRange(bodyText: String) {
        binding.tvMinPrice.text = bodyText
    }

    fun setPriceMaxRange(bodyText: String) {
        binding.tvMaxPrice.text = bodyText
    }

    fun setBackButtonListener(click: CustomViewClick?) {
        binding.ibBackButton.setOnClickListener {
            click?.goBackBefore()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun setNextFragmentButtonListener(click: CustomViewClick?) {
        if(click == null) {
            binding.ibCheck.setColorFilter(context.getColor(R.color.grey5))
        } else {
            binding.ibCheck.setColorFilter(Color.RED)
            binding.ibCheck.setOnClickListener {
                click.goNextFragment()
            }
        }
    }

}