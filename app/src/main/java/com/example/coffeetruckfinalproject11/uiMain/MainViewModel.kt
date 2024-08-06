package com.example.coffeetruckfinalproject11.uiMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainViewModel : ViewModel() {

    private val _signInSuccessLD : MutableLiveData<Boolean> = MutableLiveData()
    val signInSuccessLD = _signInSuccessLD as LiveData<Boolean>

    private val _signInErrorLD : MutableLiveData<String> = MutableLiveData()
    val signInErrorLD = _signInErrorLD as LiveData<String>

    fun signInUser(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _signInSuccessLD.postValue(true)
            } else {
                _signInErrorLD.postValue(task.exception?.message.toString())
            }
        }
    }

}