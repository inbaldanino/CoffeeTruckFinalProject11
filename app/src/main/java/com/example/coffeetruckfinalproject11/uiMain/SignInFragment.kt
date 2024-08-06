package com.example.coffeetruckfinalproject11.uiMain

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

import com.example.coffeetruckfinalproject11.R
import com.example.coffeetruckfinalproject11.UserProfileCreation
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment() {

    var emailEt: TextInputEditText ?= null
    var passwordEt: TextInputEditText ?= null

    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        passwordEt = view.findViewById(R.id.password)
        emailEt = view.findViewById(R.id.email)

        view.findViewById<Button>(R.id.login).setOnClickListener { loginUser() }
        view.findViewById<TextView>(R.id.sign_up).setOnClickListener { goToSignUpScren() }


        viewModel.signInSuccessLD.observe(viewLifecycleOwner) {
            goToMainScreen()
        }

        viewModel.signInErrorLD.observe(viewLifecycleOwner) {
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.error)
                .setMessage(it)
                .show()
        }
    }

    private fun goToSignUpScren() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, UserProfileCreation())
            ?.commit()

    }

    private fun loginUser() {
        val email = emailEt?.text?.toString() ?: ""
        val password = passwordEt?.text?.toString() ?: ""
        if (email.isNullOrEmpty() == false &&
            password.isNullOrEmpty() == false) {
            viewModel.signInUser(email, password)
        }

    }

    private fun goToMainScreen() {
    }


}