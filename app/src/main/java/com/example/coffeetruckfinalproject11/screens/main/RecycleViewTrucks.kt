package com.example.coffeetruckfinalproject11.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeetruckfinalproject11.adapters.TrucksRecyclerAdapter
import com.example.coffeetruckfinalproject11.databinding.FragmentRecycleViewTrucksBinding
import com.example.coffeetruckfinalproject11.models.CoffeeTruck
import com.example.coffeetruckfinalproject11.viewmodels.CoffeeTruckViewModel

class RecycleViewTrucks : Fragment() {

    private var _binding: FragmentRecycleViewTrucksBinding? = null
    private val binding: FragmentRecycleViewTrucksBinding get() = _binding!!


    private val viewModel by activityViewModels<CoffeeTruckViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRecycleViewTrucksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRecycleViewTrucks.layoutManager = LinearLayoutManager(requireContext())
        viewModel.coffeeTrucks.observe(viewLifecycleOwner) {
            val adapter = //get truck list
                TrucksRecyclerAdapter(it, object : TrucksRecyclerAdapter.OnItemClickListener {
                    override fun onTruckClicked(truck: CoffeeTruck) {

                    }
                })
            binding.rvRecycleViewTrucks.adapter = adapter //connect to the recyclerlist
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}