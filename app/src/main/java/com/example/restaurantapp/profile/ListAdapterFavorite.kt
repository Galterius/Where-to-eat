package com.example.restaurantapp.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.R
import com.example.restaurantapp.model.Restaurant
import kotlinx.android.synthetic.main.costum_row.view.*


class ListAdapterFavorite: RecyclerView.Adapter<ListAdapterFavorite.MyViewHolder>() {
    private var favoriteList = emptyList<Restaurant>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapterFavorite.MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.costum_row, parent, false))
    }


    override fun getItemCount(): Int {
         return favoriteList.size
    }

    override fun onBindViewHolder(holder: ListAdapterFavorite.MyViewHolder, position: Int) {
        val currentItem = favoriteList[position]
//
//        holder.itemView.favorite_id.text = currentItem.id.toString()
//        holder.itemView.restaurant_name.text = currentItem.restaurantName
        holder.itemView.textView3.text = currentItem.id.toString() + currentItem.restaurantName

    }

    fun setFavorite(favorite: List<Restaurant>){
        this.favoriteList = favorite
        notifyDataSetChanged()
    }


}