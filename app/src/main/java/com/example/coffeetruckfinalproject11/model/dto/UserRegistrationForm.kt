package com.example.coffeetruckfinalproject11.model.dto

import android.media.Image
import android.net.Uri

data class UserRegistrationForm(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val image: Uri? = null,
)