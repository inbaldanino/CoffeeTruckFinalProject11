package com.example.coffeetruckfinalproject11.screens.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

class UserProfileCreation : Fragment() {

    private var nameET: TextInputEditText? = null

    private var emailET: TextInputEditText? = null

    private var passwordET: TextInputEditText? = null

    private var saveButton: Button? = null
    private var cameraBT: Button? = null



    private var image: ImageView? = null

    private var uri: Uri?= null


    var startCamera: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(), { result ->
            if (result.resultCode === Activity.RESULT_OK) {
                uri = result.data?.data
                image?.setImageURI(uri)
            }
        }
    )

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?

    ): View? {

        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.create_user, container, false)

        setupUI(view)

        return view

    }



    private fun setupUI(view: View) {

        nameET = view.findViewById(R.id.etAddNameUser)

        passwordET = view.findViewById(R.id.etAddIdUser)

       //לבדוק איך מכניסים בעיצוב emailET = view.findViewById(R.id.email)

        saveButton = view.findViewById(R.id.saveButton)

            // לבדוק ריך מכניסים בעיצוב cameraBT =  view.findViewById(R.id.camera)

        image = view.findViewById(R.id.imageView2)

        cameraBT?.setOnClickListener { v -> openCamera() }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startCamera.launch(intent);
    }

}