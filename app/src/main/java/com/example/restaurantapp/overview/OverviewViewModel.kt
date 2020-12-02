package com.example.restaurantapp.overview

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurantapp.network.Restaurant
import com.example.restaurantapp.network.RestaurantApi
import com.example.restaurantapp.network.RestaurantsInfo
import kotlinx.coroutines.*


enum class  RestaurantApiStatus {LOADING, DONE, ERROR}

class OverviewViewModel: ViewModel(){

    private val _status = MutableLiveData<RestaurantApiStatus>()
    val status: LiveData<RestaurantApiStatus>
        get() = _status

    private val _restaurants = MutableLiveData<List<RestaurantsInfo>>()
    val restaurants: LiveData<List<RestaurantsInfo>>
        get() = _restaurants

    private val _navigateToSelectedRestaurant = MutableLiveData<RestaurantsInfo>()
    val navigateToSelectedRestaurant: LiveData<RestaurantsInfo>
    get() = _navigateToSelectedRestaurant

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getOpenTableRestaurants()
    }

    private fun getOpenTableRestaurants(){
        coroutineScope.launch {
            var getRestaurantDeferred = RestaurantApi.retrofitService.getRestaurants()
            try {
                _status.value = RestaurantApiStatus.LOADING

                var listResponse = getRestaurantDeferred.await()
                _status.value = RestaurantApiStatus.DONE

                _restaurants.value = listResponse.restaurantInfo

            }catch (It: Throwable){
                Log.i("shit", "${It}")
                _status.value = RestaurantApiStatus.ERROR
                _restaurants.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayRestaurantDetails(restaurant: RestaurantsInfo){
        _navigateToSelectedRestaurant.value = restaurant
    }

    fun displayRestaurantDetailsComplete(){
        _navigateToSelectedRestaurant.value = null
    }


}