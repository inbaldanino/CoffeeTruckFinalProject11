package com.example.coffeetruckfinalproject11.screens.auth

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.coffeetruckfinalproject11.R
import com.example.coffeetruckfinalproject11.databinding.FragmentRegisterBinding
import com.example.coffeetruckfinalproject11.model.dto.UserRegistrationForm
import com.example.coffeetruckfinalproject11.viewmodels.AuthViewModel

class RegisterFragment : Fragment() {

    private val viewModel by activityViewModels<AuthViewModel>()

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() = _binding!!

    private var selectedProfileImage: Uri? = null

    private val galleryLucher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {uri ->
        selectedProfileImage = uri
        binding.selectedImage.setImageURI(uri)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnHaveAccount.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.pickImageLayout.setOnClickListener {
            galleryLucher.launch("image/*")
        }

        binding.btnCreateAccount.setOnClickListener{
            val fullName = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if(TextUtils.isEmpty(fullName)) {
                binding.etNameLayout.error = "name must be provided"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(email)) {
                binding.etEmailLayout.error = "email must be provided"
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(password)) {
                binding.etPasswordLayout.error = "password must be provided"
                return@setOnClickListener
            }

            binding.btnCreateAccount.isEnabled = false
            viewModel.register(UserRegistrationForm(
                name = fullName,
                email = email,
                password = password,
                image = selectedProfileImage
                )
            )
            {
                binding.btnCreateAccount.isEnabled = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}