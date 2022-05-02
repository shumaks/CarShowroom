package com.example.carshowroom.screen.main.view.ui.employees

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carshowroom.screen.main.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun EmployeeView(id: Long, navController: NavHostController, viewModel: MainViewModel) {
    val employee = viewModel.employeeListStateFlow.value.first { it.id == id }
    val updateEmployee = remember { mutableStateOf(false) }
    val updatedEmployee = remember { mutableStateOf(employee) }
    val loadSpinner = remember { mutableStateOf(false) }

    if (!updateEmployee.value) {
        Column {
            Text(
                text = "${employee.surname} ${employee.name} ${employee.patr}",
                style = MaterialTheme.typography.h5
            )
            Text(text = employee.position, style = MaterialTheme.typography.h6)
            Text(text = employee.phone, style = MaterialTheme.typography.body2)
            Text(text = employee.address, style = MaterialTheme.typography.body2)

            Button(onClick = {
                updateEmployee.value = true
            }) {
                Text("Редактировать")
            }

            Button(onClick = {
                loadSpinner.value = true
                viewModel.deleteEmployee(id, navController)
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
                    value = updatedEmployee.value.surname,
                    onValueChange = {
                        updatedEmployee.value = updatedEmployee.value.copy(surname = it)
                    }
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
                    value = updatedEmployee.value.name,
                    onValueChange = {
                        updatedEmployee.value = updatedEmployee.value.copy(name = it)
                    }
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
                    value = updatedEmployee.value.patr,
                    onValueChange = {
                        updatedEmployee.value = updatedEmployee.value.copy(patr = it)
                    }
                )
            }
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Должность: ")
                TextField(
                    value = updatedEmployee.value.position,
                    onValueChange = {
                        updatedEmployee.value = updatedEmployee.value.copy(position = it)
                    }
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
                    value = updatedEmployee.value.phone,
                    onValueChange = {
                        updatedEmployee.value = updatedEmployee.value.copy(phone = it)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
            }
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Адрес: ")
                TextField(
                    value = updatedEmployee.value.address,
                    onValueChange = {
                        updatedEmployee.value = updatedEmployee.value.copy(address = it)
                    }
                )
            }
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    viewModel.updateEmployee(updatedEmployee.value, navController)
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
