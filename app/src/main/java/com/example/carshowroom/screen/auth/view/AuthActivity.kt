package com.example.carshowroom.screen.auth.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carshowroom.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_auth)
    }
}
