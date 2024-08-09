package com.example.coffeetruckfinalproject11.screens.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeetruckfinalproject11.databinding.ActivityAuthBinding
import com.example.coffeetruckfinalproject11.viewmodels.AuthViewModel
import com.example.coffeetruckfinalproject11.viewmodels.LoadingState
import com.google.android.material.snackbar.Snackbar

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    private val viewModel by viewModel<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadingState.observe(this) { state ->
            when (state) {
                is LoadingState.Loading -> {
                    binding.pBarAuth.visibility = View.VISIBLE
                }

                is LoadingState.Loaded -> {
                    binding.pBarAuth.visibility = View.GONE
                }
            }
        }
        viewModel.exception.observe(this) { exc ->
            Snackbar.make(binding.root, exc.message.toString(), Snackbar.LENGTH_LONG).show()

        }
    }
}
