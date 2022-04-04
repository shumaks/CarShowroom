package com.example.carshowroom.screen.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.carshowroom.R
import com.example.carshowroom.screen.main.view.ui.MainScreenView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContent {
            MainScreenView()
        }
    }
}
