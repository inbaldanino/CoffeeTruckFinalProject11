package com.example.coffeetruckfinalproject11.model.dto

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth

data class CoffeeTruckCreationForm(
    val name: String,
    val location: String,
    val kosher: String,
    val openingHours: String,
    val photoUri: Uri,
    val recommendations: String,
    val tripSuggestions: String,
    val user: String = FirebaseAuth.getInstance().currentUser!!.uid
)
