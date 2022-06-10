package com.example.airbnb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.data.Accommodation
import com.example.airbnb.databinding.AccommodationItemBinding
import com.example.airbnb.databinding.AccommodationPagerItemBinding

class SearchListAdapter(private val isVertical: Boolean = true) :
    ListAdapter<Accommodation, RecyclerView.ViewHolder>(diffUtil) {

    class VerticalItemViewHolder(private val binding: AccommodationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(accommodation: Accommodation) {
            binding.accommodation = accommodation
        }
    }

    class HorizontalItemViewHolder(private val binding: AccommodationPagerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(accommodation: Accommodation) {
            binding.accommodation = accommodation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (isVertical) {
            return VerticalItemViewHolder(
                AccommodationItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return HorizontalItemViewHolder(
                AccommodationPagerItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isVertical) {
            (holder as VerticalItemViewHolder).bind(getItem(position))
        } else {
            (holder as HorizontalItemViewHolder).bind(getItem(position))
        }
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<Accommodation>() {
    override fun areItemsTheSame(oldItem: Accommodation, newItem: Accommodation): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Accommodation, newItem: Accommodation): Boolean {
        return oldItem == newItem
    }

}