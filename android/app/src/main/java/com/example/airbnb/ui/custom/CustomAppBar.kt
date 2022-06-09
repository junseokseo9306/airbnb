package com.example.airbnb.ui.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.DataBindingUtil
import com.example.airbnb.R
import com.example.airbnb.databinding.CustomAppbarBinding

@BindingMethods(value = [
    BindingMethod(
        type = CustomAppBar::class,
        attribute = "app:subtitle",
        method = "setSubTitleText"
    )
])
class CustomAppBar(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {

    private var binding: CustomAppbarBinding

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
        binding.tvTitle.text = titleText
    }

    fun setSubTitleText(subtitleText: String) {
        binding.tvSubtitle.text = subtitleText
    }

    fun setBackClickListener(click: () -> Unit) {
        binding.ibBackButton.setOnClickListener {
            click()
        }
    }

    fun isValid(value: Boolean) {
        if (value) {
            binding.ibCheck.setColorFilter(Color.RED)
            binding.ibCheck.isEnabled = true
        } else {
            binding.ibCheck.setColorFilter(ContextCompat.getColor(context, R.color.grey5))
            binding.ibCheck.isEnabled = false
        }
    }

    fun setNextFragmentButtonClickListener(click: () -> Unit) {
        binding.ibCheck.setOnClickListener {
            click()
        }
    }
}