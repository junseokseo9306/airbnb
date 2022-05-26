package com.example.airbnb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.databinding.HomeCityItemBinding
import com.example.airbnb.model.City

class HomeAdapter(private val onItemClicked: () -> Unit) :
    ListAdapter<City, HomeAdapter.HomeViewHolder>(CityDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HomeViewHolder(HomeCityItemBinding.inflate(inflater, parent, false), onItemClicked)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeViewHolder(
        private val binding: HomeCityItemBinding,
        private val onItemClicked: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: City) {
            binding.city = city
            itemView.setOnClickListener {
                onItemClicked()
            }
        }
    }

    private object CityDiffUtil : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean =
            oldItem.hashCode() == newItem.hashCode()

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean =
            oldItem == newItem
    }
}