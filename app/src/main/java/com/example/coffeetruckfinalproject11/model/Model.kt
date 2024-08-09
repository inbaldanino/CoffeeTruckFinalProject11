package com.example.coffeetruckfinalproject11.model

class Model private constructor() {
    val trucks: MutableList<Truck> = ArrayList()

    companion object {
        val instance: Model = Model()
    }

    init {
        for (i in 0..20) {

            val truck = Truck("Name: $i", "Location: $i", false)
            trucks.add(truck)
        }
    }
}
