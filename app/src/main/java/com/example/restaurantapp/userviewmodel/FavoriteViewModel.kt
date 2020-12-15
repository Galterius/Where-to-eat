package com.example.restaurantapp.userviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.database.FavoriteDatabase
import com.example.restaurantapp.model.Restaurant
import com.example.restaurantapp.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Restaurant>>
    private val repository: FavoriteRepository

    init {
        val favoriteDao = FavoriteDatabase.getDatabase(application).favoriteDao()
        repository = FavoriteRepository(favoriteDao)
        readAllData = repository.readAllDatabase
    }

    fun addFavorite(restaurant: Restaurant){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRestaurant(restaurant)
        }
    }

    fun deleteFavorite(restaurant: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteOneFavorite(restaurant)
        }
    }

    fun deleteAllFavorite(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFavorite()
        }
    }


}