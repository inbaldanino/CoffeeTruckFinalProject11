package com.example.coffeetruckfinalproject11

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class UserProfileCreation : Fragment() {
    var nameTextField:EditText? = null
    var idTextField:EditText? = null
    var messageField:TextView? = null

    val saveButton: Button? = null
    val cancelButton: Button? = null

        @SuppressLint("WrongViewCast")
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?

        ): View {
            // Inflate the layout for this fragment
            val view = inflater.inflate(R.layout.fragment_user_profile_creation, container, false)

          setupUI ()
            return TODO("Provide the return value")
        }
        private fun setupUI(){
            nameTextField =findViewById(R.id.etAddNameUser)
            idTextField = findViewById(R.id.etAddIdUser)
            messageField = findViewById(R.id.textView4)

            cancelButton?.setOnClickListener(){
                finish()
            }
            saveButton?.setOnClickListener(){
                val name = nameTextField!!.text.toString()
                messageField?.text= name
            }
        }

    private fun finish() {
        TODO("Not yet implemented")
    }

    private fun findViewById(etAddNameUser: Any): EditText {
        return TODO("Provide the return value")
    }

}





