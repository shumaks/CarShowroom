package com.example.carshowroom.screen.auth.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.carshowroom.screen.auth.router.AuthRouter

class AuthViewModel(
    private val authRouter: AuthRouter
) : ViewModel() {

    fun login(login: String, password: String) {
        Log.d("Login", "successful")
        authRouter.goToMainScreen()
    }
}
