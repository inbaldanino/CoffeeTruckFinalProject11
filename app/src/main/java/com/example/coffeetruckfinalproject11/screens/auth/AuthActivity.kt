package com.example.coffeetruckfinalproject11.screens.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeetruckfinalproject11.databinding.ActivityAuthBinding
import com.example.coffeetruckfinalproject11.screens.main.MainActivity
import com.example.coffeetruckfinalproject11.viewmodels.AuthViewModel
import com.example.coffeetruckfinalproject11.viewmodels.LoadingState
import com.google.android.material.snackbar.Snackbar

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    private val viewModel by viewModels<AuthViewModel>()

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
            Log.d("Obser", exc.message.toString())
            Snackbar.make(binding.root, exc.message.toString(), Snackbar.LENGTH_LONG).show()

        }
        viewModel.user.observe(this) { user ->
            if(user != null) {
                viewModel.user.removeObservers(this)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}
