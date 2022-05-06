package com.example.carshowroom.screen.auth.view.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import com.example.carshowroom.screen.auth.viewmodel.AuthViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LoginView(viewModel: AuthViewModel) = AppTheme {
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    var wasLoginView by rememberSaveable { mutableStateOf(true) }
    val isLoginView = viewModel.loginViewStateFlow.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }
    ) {
        if (wasLoginView != isLoginView.value) {
            login = ""
            password = ""
            wasLoginView = isLoginView.value
        }

        LoginFields(
            viewModel,
            login,
            password,
            onLoginClick = { viewModel.login(login, password) },
            onLoginChange = { login = it },
            onPasswordChange = { password = it }
        )
    }
}
