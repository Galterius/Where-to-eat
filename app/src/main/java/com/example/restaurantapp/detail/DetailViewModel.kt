package com.example.restaurantapp.detail

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.example.restaurantapp.R
import com.example.restaurantapp.network.RestaurantsInfo

class DetailViewModel(restaurant: RestaurantsInfo, app: Application): AndroidViewModel(app) {
    private val _selectedRestaurant = MutableLiveData<RestaurantsInfo>()
    val selectedRestaurant: LiveData<RestaurantsInfo>
        get() = _selectedRestaurant

    private val _clicked = MutableLiveData<Boolean>()
    val clicked: LiveData<Boolean>
        get() = _clicked


    init {
        _selectedRestaurant.value = restaurant
        _clicked.value = false
    }



    fun showMaps(){
        _clicked.value = true
    }

    fun navigatedToMap(){
        _clicked.value = false
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


    val restaurantLocation = Transformations.map(selectedRestaurant){
        val gmnIntentUri = Uri.parse("geo:${it.lat},${it.lng}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmnIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
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

    fun getName(name: String){
        Log.i("res name", "${name}")
    }

}
