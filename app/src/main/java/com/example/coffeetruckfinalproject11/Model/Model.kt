package com.example.coffeetruckfinalproject11.Model

    class Model private constructor() {
        val trucks: MutableList<Truck> = ArrayList()

        companion object {
            val instance: Model by lazy { Model() }
        }
    }
