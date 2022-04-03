package com.example.carshowroom.screen.auth.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {

    fun login(login: String, password: String) {
        Log.d("Login", "successful")
    }
}
