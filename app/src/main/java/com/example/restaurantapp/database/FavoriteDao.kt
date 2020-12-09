package com.example.restaurantapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.restaurantapp.model.Restaurant

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoritRestaurant(restaurant: Restaurant)

    @Query("SELECT * FROM FAVORITE_TABLE")
    fun readAllRestaurant(): LiveData<List<Restaurant>>

    @Delete
    suspend fun deleteFavoriteRestaurant(restaurant: Restaurant)

    @Query("DELETE FROM FAVORITE_TABLE")
    suspend fun deleteAllFavorite()

}