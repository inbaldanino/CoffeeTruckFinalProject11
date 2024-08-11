package com.example.coffeetruckfinalproject11.model

import android.os.Parcel
import android.os.Parcelable

data class CoffeeTruck(
    var id: String = "",
    var name: String = "",
    var location: String = "",
    var kosher: String = "",
    var openingHours: String = "",
    var photoUri: String = "",
    var recommendations: String = "",
    var tripSuggestions: String = "",
    var reviews: List<String> = listOf(""),
    var user: String = ""
)
