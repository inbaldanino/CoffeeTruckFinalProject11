package com.example.coffeetruckfinalproject11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment


class UserProfileCreation : Fragment() {


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            // Inflate the layout for this fragment
            val view = inflater.inflate(R.layout.fragment_user_profile_creation, container, false)

            val nameTextField:EditText = findViewById(R.id.etAddNameUser)
            val idTextField:EditText = findViewById(R.id.etAddIdUser)

            return TODO("Provide the return value")
        }

    private fun findViewById(etAddNameUser: Any): EditText {

        return TODO("Provide the return value")
    }

   }





