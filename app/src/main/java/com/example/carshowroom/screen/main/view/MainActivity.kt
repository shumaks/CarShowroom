package com.example.carshowroom.screen.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.carshowroom.screen.main.view.ui.MainScreenView
import org.koin.androidx.compose.getViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        setContent {
            MainScreenView(getViewModel())
        }
    }
}
