package com.example.carshowroom.screen.main.view.ui.clients

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carshowroom.repo.client.entity.Client
import com.example.carshowroom.screen.main.view.ui.NavigationItem
import com.example.carshowroom.screen.main.viewmodel.MainViewModel
import kotlinx.coroutines.NonDisposableHandle.parent

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ClientView(id: Long, navController: NavHostController, viewModel: MainViewModel) {
    val client = viewModel.clientsListStateFlow.value.first { it.id == id }
    val updateClient = remember { mutableStateOf(false) }
    val updatedClient = remember { mutableStateOf(client) }
    val loadSpinner = remember { mutableStateOf(false) }

    if (!updateClient.value) {
        Column {
            Text(text = "${client.surname} ${client.name} ${client.patr}", style = typography.h6)
            Text(text = client.phone, style = typography.body2)
            Button(onClick = {
                updateClient.value = true
            }) {
                Text("Редактировать")
            }
            Button(onClick = {
                loadSpinner.value = true
                viewModel.deleteClient(id, navController)
            }) {
                Text("Удалить")
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
    } else {
        Column {
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Фамилия: ")
                TextField(
                    value = updatedClient.value.surname,
                    onValueChange = { updatedClient.value = updatedClient.value.copy(surname = it) }
                )
            }
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Имя:  ")
                TextField(
                    value = updatedClient.value.name,
                    onValueChange = { updatedClient.value = updatedClient.value.copy(name = it) }
                )
            }
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Отчество: ")
                TextField(
                    value = updatedClient.value.patr,
                    onValueChange = { updatedClient.value = updatedClient.value.copy(patr = it) }
                )
            }
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Номер телефона: ")
                TextField(
                    value = updatedClient.value.phone,
                    onValueChange = { updatedClient.value = updatedClient.value.copy(phone = it) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
            }
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    viewModel.updateClient(updatedClient.value, navController)
                    loadSpinner.value = true
                }) {
                    Text("Обновить")
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
}
