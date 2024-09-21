package com.example.coffeetruckfinalproject11

data class Resource<T>(
    var loading: Boolean = true,
    var data: T? = null,
)
