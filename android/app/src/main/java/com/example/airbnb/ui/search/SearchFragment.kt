package com.example.airbnb.ui.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.airbnb.R
import com.example.airbnb.adapters.CityItemAdapter
import com.example.airbnb.data.SearchFilter
import com.example.airbnb.databinding.FragmentSearchBinding
import com.example.airbnb.ui.calendar.CustomCalendar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private var isInputtedText = false

    private val args: SearchFragmentArgs by navArgs()

    private lateinit var customCalendar: CustomCalendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(
            layoutInflater,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backMainButton.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_homeFragment)
        }
        registerTextViewForTextChange()
        registerCloseButton()
        focusInputTextAndShowKeyboard()

        val adapter = CityItemAdapter { cityName ->
            val action = R.id.action_searchFragment_to_priceBarFragment
            customCalendar = CustomCalendar(
                this@SearchFragment,
                action,
                SearchFilter(cityName, null, null, null)
            )
            customCalendar.setUpDefaultCalendar()
        }
        binding.cityRecyclerview.adapter = adapter
        adapter.submitList(args.cityInfoList.toList())
    }

    private fun focusInputTextAndShowKeyboard() {
        binding.searchInputText.requestFocus()

        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.searchInputText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun registerCloseButton() {
        binding.clearButton.setOnClickListener {
            if (isInputtedText) binding.searchInputText.text.clear()
        }
    }

    private fun registerTextViewForTextChange() {
        binding.searchInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isInputtedText = if (s.isNullOrBlank()) {
                    binding.clearButton.setImageResource(R.drawable.ic_search)
                    binding.nearTripText.isVisible = true
                    false
                } else {
                    binding.clearButton.setImageResource(R.drawable.ic_clear)
                    binding.nearTripText.isVisible = false
                    true
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}