package com.example.carshowroom.screen.auth.view.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.carshowroom.screen.auth.viewmodel.AuthViewModel

@Composable
fun LoginFields(
    viewModel: AuthViewModel,
    login: String,
    password: String,
    onLoginClick: (login: String) -> Unit,
    onLoginChange: (login: String) -> Unit,
    onPasswordChange: (password: String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val isLoading by viewModel.loadingStateFlow.collectAsState()
    val isLoginView by viewModel.loginViewStateFlow.collectAsState()
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val title = if (isLoginView) {
            "Авторизация"
        } else {
            "Регистрация"
        }
        Text(title)
        OutlinedTextField(
            value = login,
            placeholder = { Text(text = "Логин") },
            label = { Text(text = "Логин") },
            onValueChange = onLoginChange,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )

        OutlinedTextField(
            value = password,
            placeholder = { Text(text = "Пароль") },
            label = { Text(text = "Пароль") },
            onValueChange = onPasswordChange,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                val description = if (passwordVisible) "Скрыть" else "Показать"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector  = image, description)
                }
            }
        )

        if (isLoginView) {
            Button(onClick = {
                if (login.isNotBlank() && password.isNotBlank()) {
                    onLoginClick(login)
                    focusManager.clearFocus()
                } else {
                    viewModel.showLoginErrorMessage()
                }
            }) {
                Text("Войти")
            }

            Button(onClick = {
                viewModel.loginViewStateFlow.value = false
            }) {
                Text("Регистрация")
            }
        } else {
            Button(onClick = {
                if (login.isNotBlank() && password.isNotBlank()) {
                    viewModel.registerUser(login, password)
                    focusManager.clearFocus()
                } else {
                    viewModel.showLoginErrorMessage()
                }
            }) {
                Text("Ок")
            }
        }

        if (isLoading) {
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
