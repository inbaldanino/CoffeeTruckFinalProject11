package com.example.coffeetruckfinalproject11.model

import com.example.coffeetruckfinalproject11.Model.Truck

class Model private constructor() {
    val trucks: MutableList<Truck> = ArrayList()

    companion object {
        val instance: Model by lazy { Model() }
    }

    init {
        for (i in 0..20) {
            val truck = Truck("Name: $i", "Location: $i", false)
            trucks.add(truck)
        }
    }
}
