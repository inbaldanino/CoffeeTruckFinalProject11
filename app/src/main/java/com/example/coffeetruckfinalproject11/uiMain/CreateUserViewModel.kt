package com.example.coffeetruckfinalproject11.uiMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CreateUserViewModel : ViewModel() {

    private val _signUpSuccessLD : MutableLiveData<Boolean> = MutableLiveData()
    val signUpSuccessLD = _signUpSuccessLD as LiveData<Boolean>

    private val _signUpErrorLD : MutableLiveData<String> = MutableLiveData()
    val signUpErrorLD = _signUpErrorLD as LiveData<String>

    fun signInUser(email: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){

            } else {
                _signUpErrorLD.postValue(it.exception?.message?.toString())
            }
        }

    }

}