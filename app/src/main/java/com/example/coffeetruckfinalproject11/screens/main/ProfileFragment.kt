package com.example.coffeetruckfinalproject11.screens.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.coffeetruckfinalproject11.databinding.FragmentProfileBinding
import com.example.coffeetruckfinalproject11.viewmodels.CoffeeTruckViewModel
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!
    private val viewModel: CoffeeTruckViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signOutBtn.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Sign out")
                .setMessage("Are you sure you want to sign out")
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.logOut()

                }
                .setNegativeButton("No", null)
                .show()
        }

        viewModel.user.observe(viewLifecycleOwner) {
            it.data?.let { user ->
                binding.nameProfile.text = user.name
                binding.emailProfile.text = user.email
                Picasso.get()
                    .load(user.image)
                    .into(binding.imageProfile)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}