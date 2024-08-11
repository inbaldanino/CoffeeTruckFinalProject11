package com.example.coffeetruckfinalproject11.screens.main

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.coffeetruckfinalproject11.viewmodels.CoffeeTruckViewModel
import com.example.coffeetruckfinalproject11.R
import com.example.coffeetruckfinalproject11.database.Database
import com.example.coffeetruckfinalproject11.databinding.ActivityMainBinding
import com.example.coffeetruckfinalproject11.viewmodels.LoadingState
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val coffeeTruckViewModel: CoffeeTruckViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        coffeeTruckViewModel.loadingState.observe(this) { state ->
            when (state) {
                is LoadingState.Loading -> {
                    binding.pBarAuth.visibility = View.VISIBLE
                }

                is LoadingState.Loaded -> {
                    binding.pBarAuth.visibility = View.GONE
                }
            }
        }

        coffeeTruckViewModel.exception.observe(this) { exc ->
            Snackbar.make(binding.root, exc.message.toString(), Snackbar.LENGTH_LONG).show()
        }


        val bottomNavigation = binding.bottomNavView

        val fragmentContainer = supportFragmentManager.findFragmentById(R.id.mainNavGraph)


        val navController = fragmentContainer!!.findNavController()

        NavigationUI.setupWithNavController(bottomNavigation, navController)


    }


    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("super.onBackPressed()", "androidx.appcompat.app.AppCompatActivity")
    )
    override fun onBackPressed() {
        if (!findNavController(R.id.mainNavGraph).popBackStack()) {
            super.onBackPressed()
        }
    }
}