package com.example.restaurantapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
data class Restaurant(
    val total_entries: Int,
    val page: Int,
    val per_page: Int,
    @Json(name = "restaurants") val restaurantInfo: List<RestaurantsInfo>
): Parcelable

