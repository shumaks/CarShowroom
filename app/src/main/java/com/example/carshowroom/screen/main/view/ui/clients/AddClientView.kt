package com.example.carshowroom.screen.main.view.ui.clients

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carshowroom.repo.client.entity.Client
import com.example.carshowroom.screen.main.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AddClientView(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val newClient = remember { mutableStateOf(
        Client(
            id = 0,
            surname = "",
            name = "",
            patr = "",
            phone  = ""
        )
    ) }
    val loadSpinner = remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Фамилия: ")
            newClient.value.surname.let {
                TextField(
                    value = it,
                    onValueChange = { newClient.value = newClient.value.copy(surname = it) }
                )
            }
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Имя: ")
            newClient.value.name.let {
                TextField(
                    value = it,
                    onValueChange = { newClient.value = newClient.value.copy(name = it) }
                )
            }
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Отчество: ")
            newClient.value.patr.let {
                TextField(
                    value = it,
                    onValueChange = { newClient.value = newClient.value.copy(patr = it) }
                )
            }
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Номер телефона: ")
            newClient.value.phone.let {
                TextField(
                    value = it,
                    onValueChange = { newClient.value = newClient.value.copy(phone = it) }
                )
            }
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                viewModel.addClient(newClient.value, navController)
                loadSpinner.value = true
            }) {
                Text("Добавить")
            }
        }
        if (loadSpinner.value) {
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
