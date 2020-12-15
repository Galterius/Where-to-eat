package com.example.restaurantapp.repository

import androidx.lifecycle.LiveData
import com.example.restaurantapp.database.FavoriteDao
import com.example.restaurantapp.model.Restaurant

class FavoriteRepository(private  val favoriteDao: FavoriteDao) {
    val readAllDatabase: LiveData<List<Restaurant>> = favoriteDao.readAllRestaurant()

    suspend fun addRestaurant(restaurant: Restaurant){
        favoriteDao.addFavoritRestaurant(restaurant)
    }

    suspend fun deleteAllFavorite(){
        favoriteDao.deleteAllFavorite()
    }

    suspend fun deleteOneFavorite(restaurant_name: String){
        favoriteDao.deleteOneFavorite(restaurant_name)
    }
}