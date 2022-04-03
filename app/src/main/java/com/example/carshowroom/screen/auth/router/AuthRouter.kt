package com.example.carshowroom.screen.auth.router

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.example.carshowroom.screen.main.view.MainActivity

class AuthRouter(
    private val context: Context
) {

    fun goToMainScreen() {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        startActivity(context, intent, Bundle())
    }
}
