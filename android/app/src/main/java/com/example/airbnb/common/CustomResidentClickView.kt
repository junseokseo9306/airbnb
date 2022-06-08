package com.example.airbnb.common

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.example.airbnb.R
import com.example.airbnb.databinding.CustomResidentCountBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@RequiresApi(Build.VERSION_CODES.M)
class CustomResidentClickView(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {

    private lateinit var binding: CustomResidentCountBinding

    private var _clickCounts: MutableStateFlow<Int> = MutableStateFlow(0)
    var clickCounts = _clickCounts.asStateFlow()

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
        binding.tvAdult.text = text
    }

    private fun setBody(text: String) {
        binding.tvBody.text = text
    }

    private fun clickButton() {
        with(binding) {
            var adultCount = tvAdultCount.text.toString().toInt()
            _clickCounts.value = adultCount
            setColor(ibAdultMinus, R.color.grey5)
            ibAdultMinus.setOnClickListener {
                if (adultCount-- <= 0) {
                    setColor(ibAdultMinus, R.color.grey5)
                } else {
                    if (adultCount == 0) {
                        _clickCounts.value = 0
                        setColor(ibAdultMinus, R.color.grey5)
                        binding.tvAdultCount.text = adultCount.toString()
                    } else {
                        _clickCounts.value = adultCount
                        ibAdultMinus.setColorFilter(Color.BLACK)
                        binding.tvAdultCount.text = adultCount.toString()
                    }
                }
            }
            ibAdultAdd.setOnClickListener {
                ibAdultMinus.setColorFilter(Color.BLACK)
                adultCount++
                _clickCounts.value = adultCount
                binding.tvAdultCount.text = adultCount.toString()
            }
        }
    }

    private fun setColor(view: ImageButton, color: Int) {
        context.let { view.setColorFilter(it.getColor(color)) }
    }
}
