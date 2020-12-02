package com.example.restaurantapp

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.restaurantapp.network.Restaurant
import com.example.restaurantapp.network.RestaurantsInfo
import com.example.restaurantapp.overview.PhotoGridAdapter
import com.example.restaurantapp.overview.RestaurantApiStatus

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<RestaurantsInfo>?){
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context).load(imgUri).apply(RequestOptions().placeholder(R.drawable.loading_animation).error(R.drawable.ic_broken_image)).into(imgView)
    }
}


@BindingAdapter("restaurantApiStatus")
fun bindStatus(statusImageView: ImageView, status: RestaurantApiStatus?){
    when(status){
        RestaurantApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        RestaurantApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        RestaurantApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }

}