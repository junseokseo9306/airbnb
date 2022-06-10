package com.example.airbnb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.airbnb.data.CityInfo
import com.example.airbnb.databinding.HomeCityItemBinding

class CityItemAdapter(private val onItemClicked: (cityName: String) -> Unit) :
    ListAdapter<CityInfo, CityItemAdapter.HomeViewHolder>(CityDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HomeViewHolder(
            HomeCityItemBinding.inflate(inflater, parent, false),
            onItemClicked
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeViewHolder(
        private val binding: HomeCityItemBinding,
        private val onItemClicked: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cityInfo: CityInfo) {
            binding.cityInfo = cityInfo
            itemView.setOnClickListener {
                onItemClicked(cityInfo.city.cityName)
            }
        }
    }

    private object CityDiffUtil : DiffUtil.ItemCallback<CityInfo>() {
        override fun areItemsTheSame(oldItem: CityInfo, newItem: CityInfo): Boolean =
            oldItem.hashCode() == newItem.hashCode()

        override fun areContentsTheSame(oldItem: CityInfo, newItem: CityInfo): Boolean =
            oldItem == newItem
    }
}