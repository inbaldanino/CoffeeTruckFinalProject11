package com.example.coffeetruckfinalproject11.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.coffeetruckfinalproject11.R
import com.example.coffeetruckfinalproject11.databinding.FragmentDisplayCoffeeTruckBinding
import com.example.coffeetruckfinalproject11.model.CoffeeTruck
import com.example.coffeetruckfinalproject11.viewmodels.CoffeeTruckViewModel
import com.google.gson.Gson

class DisplayCoffeeTruck : Fragment() {

    private var _binding: FragmentDisplayCoffeeTruckBinding? = null
    private val binding: FragmentDisplayCoffeeTruckBinding get() = _binding!!
    private val params: DisplayCoffeeTruckArgs by navArgs()

    private val viewModel : CoffeeTruckViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_coffee_truck, container, false)
        _binding = FragmentDisplayCoffeeTruckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val truck = Gson().fromJson(params.truck, CoffeeTruck::class.java)
        binding.textViewKosher.text = "כשרות: ${truck.kosher}"
        binding.textViewLocation.text = "מיקום: ${truck.location}"
        binding.textViewOpeningHours.text = " שעות פתיחה : ${truck.openingHours}"
        binding.textViewName.text = "שם העגלה: ${truck.name}"
        binding.textViewTripSuggestions.text = "המלצות: ${truck.tripSuggestions}"
        binding.textViewRecommendations.text = "ביקורות: ${truck.recommendations}"

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            truck.reviews
        )
        binding.reviewsListView.adapter = adapter
        binding.addReview.setOnClickListener()
        {
            val reviewView = layoutInflater.inflate(R.layout.review_layout, null, false)
            val reviewTv = reviewView.findViewById<EditText>(R.id.etReview)
            val alertDialog = AlertDialog.Builder(requireContext())
                .setTitle("Add review for ${truck.name}")
                .setView(reviewView)
                .setPositiveButton("Submit") {_,_ ->
                    val review = reviewTv.text.toString()
                    if (review.isEmpty())
                    {
                        Toast.makeText(
                            requireContext(),
                            "Can not post an empty review",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else{
                        viewModel.addReview(truck, review)

                        {
                            Toast.makeText(requireContext(),
                                "Review was posted successfully",
                                Toast.LENGTH_LONG)
                                .show()
                            adapter.add(review)
                            //Will refresh the list
                            adapter.notifyDataSetChanged()
                        }
                }
        }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}