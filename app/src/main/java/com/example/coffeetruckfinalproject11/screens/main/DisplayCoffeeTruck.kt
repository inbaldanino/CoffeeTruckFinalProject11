package com.example.coffeetruckfinalproject11.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coffeetruckfinalproject11.R

/**
 * A simple [Fragment] subclass.
 * Use the [DisplayCoffeeTruck.newInstance] factory method to
 * create an instance of this fragment.
 */
class DisplayCoffeeTruck : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_coffee_truck, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DisplayCoffeeTruck.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DisplayCoffeeTruck().apply {
                arguments = Bundle().apply {
                }
            }
    }
}