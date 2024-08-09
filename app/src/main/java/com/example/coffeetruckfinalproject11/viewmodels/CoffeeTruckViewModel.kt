package com.example.coffeetruckfinalproject11.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coffeetruckfinalproject11.model.CoffeeTruck

class CoffeeTruckViewModel : ViewModel() {
    private val _coffeeTrucks = MutableLiveData<MutableList<CoffeeTruck>>(mutableListOf())
    val coffeeTrucks: LiveData<MutableList<CoffeeTruck>> = _coffeeTrucks

    fun addCoffeeTruck(coffeeTruck: CoffeeTruck) {
        _coffeeTrucks.value?.add(coffeeTruck)
        _coffeeTrucks.value = _coffeeTrucks.value // Trigger observer
    }
}
