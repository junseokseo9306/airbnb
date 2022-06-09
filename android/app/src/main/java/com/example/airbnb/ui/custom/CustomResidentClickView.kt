package com.example.airbnb.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.DataBindingUtil
import com.example.airbnb.R
import com.example.airbnb.databinding.CustomResidentCountBinding

@BindingMethods(value = [
    BindingMethod(
        type = CustomResidentClickView::class,
        attribute = "custom:value",
        method = "setCount"
    )
])
class CustomResidentClickView(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    private var binding: CustomResidentCountBinding

    interface OnChangeClickCountListener {

        fun onChanged(@IdRes id: Int, step: Int)
    }

    private var onChangeClickCountListener: OnChangeClickCountListener? = null
    fun setOnChangeClickCountListener(listener: OnChangeClickCountListener) {
        onChangeClickCountListener = listener
    }

    private var count = 0

    init {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.custom_resident_count,
            this,
            true
        )
        getAttrs(attrs)
        clickButton()
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomResidentClickView)

        setTitle(typedArray.getString(R.styleable.CustomResidentClickView_titleResident) as String)
        setBody(typedArray.getString(R.styleable.CustomResidentClickView_bodyResident) as String)

        typedArray.recycle()
    }

    private fun setTitle(text: String) {
        binding.tvResidentTitle.text = text
    }

    private fun setBody(text: String) {
        binding.tvResidentTitle.text = text
    }

    private fun clickButton() {
        with(binding) {
            setColor(subtractCountButton, R.color.grey5)
            subtractCountButton.setOnClickListener {
                count--
                if (count == 0) {
                    setColor(subtractCountButton, R.color.grey5)
                    subtractCountButton.isEnabled = false
                }

                residentCount.text = count.toString()
                onChangeClickCountListener?.onChanged( id,-1)
            }

            addCountButton.setOnClickListener {
                count++
                setColor(subtractCountButton, R.color.black)
                subtractCountButton.isEnabled = true
                residentCount.text = count.toString()
                onChangeClickCountListener?.onChanged(id,1)
            }
        }
    }

    private fun setColor(view: ImageButton, @ColorRes color: Int) {
        view.setColorFilter(
            ContextCompat.getColor(context, color)
        )
    }

    fun setCount(count: Int) = with(binding) {
        if (count == 0) {
            subtractCountButton.isEnabled = false
            setColor(subtractCountButton, R.color.grey5)
        } else {
            subtractCountButton.isEnabled = true
            setColor(subtractCountButton, R.color.black)
        }

        residentCount.text = count.toString()
    }
}
