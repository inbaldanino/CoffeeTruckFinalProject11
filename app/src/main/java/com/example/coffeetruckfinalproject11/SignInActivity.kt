package com.example.coffeetruckfinalproject11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coffeetruckfinalproject11.ui.main.SignInFragment

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SignInFragment())
                .commitNow()
        }
    }
}