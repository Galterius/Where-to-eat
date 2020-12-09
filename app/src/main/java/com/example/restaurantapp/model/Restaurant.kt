package com.example.restaurantapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorite_table")
data class Restaurant(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val restaurantName: String
): Parcelable

