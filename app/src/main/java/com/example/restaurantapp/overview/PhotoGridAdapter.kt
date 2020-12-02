package com.example.restaurantapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.databinding.GridViewBinding
import com.example.restaurantapp.network.Restaurant
import com.example.restaurantapp.network.RestaurantsInfo

class PhotoGridAdapter(private val onClickListener: OnClickListener): ListAdapter<RestaurantsInfo, PhotoGridAdapter.RestaurantViewHolder>(DiffCallback) {

    class RestaurantViewHolder(private val binding: GridViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(restaurant: RestaurantsInfo){
            binding.restaurant = restaurant
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<RestaurantsInfo>(){

        override fun areItemsTheSame(oldItem: RestaurantsInfo, newItem: RestaurantsInfo): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RestaurantsInfo, newItem: RestaurantsInfo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.RestaurantViewHolder {
        return RestaurantViewHolder(GridViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.RestaurantViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(restaurant)
        }
        holder.bind(restaurant)
    }

    class OnClickListener(val clickListener: (restaurant: RestaurantsInfo)-> Unit){
        fun onClick(restaurant: RestaurantsInfo) = clickListener(restaurant)
    }
}