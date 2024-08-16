package com.example.coffeetruckfinalproject11.screens.main

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.coffeetruckfinalproject11.databinding.FragmentAddNewCoffeeTruckBinding
import com.example.coffeetruckfinalproject11.model.dto.CoffeeTruckCreationForm
import com.example.coffeetruckfinalproject11.viewmodels.CoffeeTruckViewModel

class AddNewCoffeeTruck : Fragment() {
    private val coffeeTruckViewModel: CoffeeTruckViewModel by activityViewModels()


    private var _binding: FragmentAddNewCoffeeTruckBinding? = null
    private val binding: FragmentAddNewCoffeeTruckBinding get() = _binding!!
    private var selectedCoffeeTruckImage: Uri? = null

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        selectedCoffeeTruckImage = uri
        binding.selectedImage.setImageURI(uri)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddNewCoffeeTruckBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pickImageLayout.setOnClickListener {
            galleryLauncher.launch("image/*")
        }

        binding.buttonSubmit.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val location = binding.editTextLocation.text.toString()
            val kosher = binding.editTextKosher.text.toString()
            val openingHours = binding.editTextOpeningHours.text.toString()
            val recommendations = binding.editTextRecommendations.text.toString()
            val tripSuggestions = binding.editTextTripSuggestions.text.toString()


            if (validateInputs()) {
                val coffeeTruckForm = CoffeeTruckCreationForm(
                   name= name,
                    location= location,
                    kosher=kosher,
                    openingHours=openingHours,
                    photoUri = selectedCoffeeTruckImage!!,
                    recommendations= recommendations,
                    tripSuggestions= tripSuggestions,
                )
                binding.buttonSubmit.isEnabled = false
                coffeeTruckViewModel.addCoffeeTruck(coffeeTruckForm)
                {
                    binding.buttonSubmit.isEnabled = true
                }
                findNavController().popBackStack()
            }

        }
    }
    private fun validateInputs(): Boolean {
        val name = binding.editTextName.text.toString()
        val location = binding.editTextLocation.text.toString()
        val kosher = binding.editTextKosher.text.toString()
        val openingHours = binding.editTextOpeningHours.text.toString()
        val recommendations = binding.editTextRecommendations.text.toString()
        val tripSuggestions = binding.editTextTripSuggestions.text.toString()


        if (selectedCoffeeTruckImage == null) {
            Toast.makeText(requireContext(), "Image must be selected!", Toast.LENGTH_SHORT).show()
            return false
        }
        return when {
            name.isEmpty() -> {
                Toast.makeText(requireContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show()
                false
            }


            location.isEmpty() -> {
                Toast.makeText(requireContext(), "Location cannot be empty", Toast.LENGTH_SHORT)
                    .show()
                false
            }

            kosher.isEmpty() -> {
                Toast.makeText(requireContext(), "Kosher cannot be empty", Toast.LENGTH_SHORT)
                    .show()
                false
            }

            openingHours.isEmpty() -> {
                Toast.makeText(
                    requireContext(),
                    "Opening Hours cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
                false
            }

            recommendations.isEmpty() -> {
                Toast.makeText(
                    requireContext(),
                    "Recommendations cannot be empty",
                    Toast.LENGTH_SHORT
                )
                    .show()
                false
            }

            tripSuggestions.isEmpty() -> {
                Toast.makeText(
                    requireContext(),
                    "Trip Suggestions cannot be empty",
                    Toast.LENGTH_SHORT
                )
                    .show()
                false
            }
            else -> true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}