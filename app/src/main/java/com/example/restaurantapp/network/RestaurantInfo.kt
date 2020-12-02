package com.example.restaurantapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RestaurantsInfo(
        val id: Int,
        val name: String,
        val address: String,
        val city: String,
        val state: String,
        val area: String,
        @Json(name = "postal_code") val postalCode: String,
        val country: String,
        val phone: String,
        val lat: Float,
        val lng: Float,
        val price: Int,
        @Json(name = "reserve_url") val reserverUrl: String,
        @Json(name = "mobile_reserve_url") val mobileReserveUrl: String,
        @Json(name = "image_url") val imgUrl: String
): Parcelable