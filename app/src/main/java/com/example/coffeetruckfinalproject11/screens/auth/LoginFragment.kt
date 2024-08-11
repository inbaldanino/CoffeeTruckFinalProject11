package com.example.coffeetruckfinalproject11.screens.auth

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.coffeetruckfinalproject11.R
import com.example.coffeetruckfinalproject11.databinding.FragmentLoginBinding
import com.example.coffeetruckfinalproject11.viewmodels.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val viewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNoAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (TextUtils.isEmpty(email)) {
                binding.etEmailLayout.error = "Email must not be empty"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                binding.etPasswordLayout.error = "Password must not be empty"
                return@setOnClickListener
            }
            binding.btnLogin.isEnabled = false
            viewModel.login(email, password) {
                binding.btnLogin.isEnabled = true
            }
        }
    }

        /*FirebaseAuth.getInstance().createUserWithEmailAndPassword(
        "yael_123451@walla.com",
        "123456"
    )

     */
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
}
