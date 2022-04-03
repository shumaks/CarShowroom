package com.example.carshowroom.screen.auth.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import com.example.carshowroom.screen.auth.viewmodel.AuthViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun LoginView(viewModel: AuthViewModel) = AppTheme {
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }
    ) {
        LoginFields(
            login,
            password,
            onLoginClick = { viewModel.login(login, password) },
            onEmailChange = { login = it },
            onPasswordChange = { password = it }
        )
    }
}
