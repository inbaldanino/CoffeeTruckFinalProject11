package com.example.coffeetruckfinalproject11



import android.os.Bundle

import android.view.LayoutInflater

import android.view.View

import android.view.ViewGroup

import android.widget.Button

import android.widget.EditText

import android.widget.TextView

import androidx.fragment.app.Fragment



class UserProfileCreation : Fragment() {

    private var nameTextField: EditText? = null

    private var idTextField: EditText? = null

    private var messageField: TextView? = null

    private var saveButton: Button? = null

    private var cancelButton: Button? = null



    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?

    ): View? {

        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_user_profile_creation, container, false)

        setupUI(view)

        return view

    }



    private fun setupUI(view: View) {

        nameTextField = view.findViewById(R.id.etAddNameUser)

        idTextField = view.findViewById(R.id.etAddIdUser)

        messageField = view.findViewById(R.id.textView4)

        saveButton = view.findViewById(R.id.saveButton)

        cancelButton = view.findViewById(R.id.cancelButton)



        cancelButton?.setOnClickListener {

            activity?.finish()

        }

        saveButton?.setOnClickListener {

            val name = nameTextField?.text.toString()

            messageField?.text = name

        }

    }

}

