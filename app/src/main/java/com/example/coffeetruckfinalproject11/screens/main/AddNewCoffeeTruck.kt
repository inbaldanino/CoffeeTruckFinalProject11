package com.example.coffeetruckfinalproject11.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.coffeetruckfinalproject11.model.CoffeeTruck

class AddNewCoffeeTruck : Fragment()
{
    // private val coffeeTruckViewModel: CoffeeTruckViewModel by activityViewModels()

    private lateinit var editTextName: EditText
    private lateinit var editTextLocation: EditText
    private lateinit var editTextKosher: EditText
    private lateinit var editTextOpeningHours: EditText
    private lateinit var editTextRecommendations: EditText
    private lateinit var editTextTripSuggestions: EditText
    private lateinit var editTextReviews: EditText
    private lateinit var buttonSubmit: Button
    //
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_new_coffee_truck, container, false)

        editTextName = view.findViewById(R.id.editTextName)
        editTextLocation = view.findViewById(R.id.editTextLocation)
        editTextKosher = view.findViewById(R.id.editTextKosher)
        editTextOpeningHours = view.findViewById(R.id.editTextOpeningHours)
        editTextRecommendations = view.findViewById(R.id.editTextRecommendations)
        editTextTripSuggestions = view.findViewById(R.id.editTextTripSuggestions)
        editTextReviews = view.findViewById(R.id.editTextReviews)
        buttonSubmit = view.findViewById(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            val name = editTextName.text.toString()
            val location = editTextLocation.text.toString()
            val kosher = editTextKosher.text.toString()
            val openingHours = editTextOpeningHours.text.toString()
            val recommendations = editTextRecommendations.text.toString()
            val tripSuggestions = editTextTripSuggestions.text.toString()
            val reviews = editTextReviews.text.toString()
            val photoUri: String? = null

            val coffeeTruck = CoffeeTruck(
                name,
                location,
                kosher,
                openingHours,
                photoUri,
                recommendations,
                tripSuggestions,
                reviews
            )
            //coffeeTruckViewModel.addCoffeeTruck(coffeeTruck)

            // Close the fragment
            parentFragmentManager.popBackStack()
            // Use the collected data (e.g., save to a database or send to an API)
        }
        return view
    }

}