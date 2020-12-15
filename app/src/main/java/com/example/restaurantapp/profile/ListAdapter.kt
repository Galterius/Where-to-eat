package com.example.restaurantapp.profile

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.R
import com.example.restaurantapp.detail.DetailViewModel
import com.example.restaurantapp.model.Restaurant
import com.example.restaurantapp.model.User
import com.example.restaurantapp.userviewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.costum_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()
    private var favoriteList: String = ""
    var test: String = ""

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.costum_row, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

/**
 *This crap doesnt work idk what to do FUCK IT
**/


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.itemView.id_text.text = currentItem.id.toString()
        holder.itemView.firstName_txt.text = currentItem.firstName
        holder.itemView.lastName_txt.text = currentItem.lastName
        holder.itemView.age_txt.text = currentItem.age.toString()

       holder.itemView.textView3.text = favoriteList


        //when the user selects a row we will navigate to the selected rows update fragment
        holder.itemView.rowLayout.setOnClickListener {
            val action = UserProfileFragmentDirections.actionUserProfileToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

    fun setFavorite(favorite: List<Restaurant>){
        if (favorite.isNotEmpty()) {
            val iterator = favorite.iterator()
            var temp: String = ""
            iterator.forEach {
                temp += "${it.id}: ${it.restaurantName} \n"
            }

            test = favorite.groupingBy { it.restaurantName }.eachCount().filter { it.value > 1 }.toString().split("\\=".toRegex())[0].split("\\{".toRegex())[1]

            this.favoriteList = temp
            notifyDataSetChanged()
        }
//        Log.i("test2","${favoriteList}")
    }

}