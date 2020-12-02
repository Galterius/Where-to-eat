package com.example.restaurantapp.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.restaurantapp.R
import com.example.restaurantapp.network.Restaurant
import com.example.restaurantapp.network.RestaurantsInfo

class DetailViewModel(restaurant: RestaurantsInfo, app: Application): AndroidViewModel(app) {
    private val _selectedRestaurant = MutableLiveData<RestaurantsInfo>()
    val selectedRestaurant: LiveData<RestaurantsInfo>
        get() = _selectedRestaurant

    init {
        _selectedRestaurant.value = restaurant
    }

    val displayRestaurantName = Transformations.map(selectedRestaurant){
        app.applicationContext.getString(R.string.restaurant_name, it.name)
    }

    val displayRestaurantAddress = Transformations.map(selectedRestaurant){
        app.applicationContext.getString(R.string.restaurant_address, it.address)
    }

    val displayRestaurantCity = Transformations.map(selectedRestaurant){
        app.applicationContext.getString(R.string.restaurant_city, it.city)
    }

    val displayRestaurantState = Transformations.map(selectedRestaurant){
        app.applicationContext.getString(R.string.restaurant_state, it.state)
    }

    val displayRestaurantArea = Transformations.map(selectedRestaurant){
        app.applicationContext.getString(R.string.restaurant_area, it.area)
    }

    val displayRestaurantPostalCode = Transformations.map(selectedRestaurant){
        app.applicationContext.getString(R.string.restaurant_postalCode, it.postalCode)
    }

    val displayRestaurantCountry = Transformations.map(selectedRestaurant){
        app.applicationContext.getString(R.string.restaurant_country, it.country)
    }

    val displayRestaurantPhone = Transformations.map(selectedRestaurant){
        app.applicationContext.getString(R.string.restaurant_phone, it.phone)
    }

    val displayRestaurantLat = Transformations.map(selectedRestaurant){
        app.applicationContext.getString(R.string.restaurant_lat, it.lat)
    }

    val displayRestaurantLng = Transformations.map(selectedRestaurant){
        app.applicationContext.getString(R.string.restaurant_lng, it.lng)
    }

    val displayRestaurantPrice = Transformations.map(selectedRestaurant){
        app.applicationContext.getString(R.string.restaurant_price, it.price)
    }

    val displayRestaurantReserveUrl = Transformations.map(selectedRestaurant){
        app.applicationContext.getString(R.string.restaurant_reserveUrl, it.reserverUrl)
    }

    val displayRestaurantMobileReserveUrl = Transformations.map(selectedRestaurant){
        app.applicationContext.getString(R.string.restaurant_mobileReserveUrl, it.mobileReserveUrl)
    }


}
