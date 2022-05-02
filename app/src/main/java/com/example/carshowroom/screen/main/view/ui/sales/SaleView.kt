package com.example.carshowroom.screen.main.view.ui.sales

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carshowroom.screen.main.view.ui.auto.dropDownMenu
import com.example.carshowroom.screen.main.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SaleView(id: Long, navController: NavHostController, viewModel: MainViewModel) {
    val sale = viewModel.salesListStateFlow.value.first { it.id == id }
    val updateSale = remember { mutableStateOf(false) }
    val updatedSale = remember { mutableStateOf(sale) }
    val loadSpinner = remember { mutableStateOf(false) }

    if (!updateSale.value) {
        Column {
            Text(
                text = "Дата: ${sale.date}",
                style = MaterialTheme.typography.h6
            )
            Text(text = "Сотрудник: ${sale.employee.surname} ${sale.employee.name} ${sale.employee.patr}", style = MaterialTheme.typography.body2)
            Text(text = "Клиент: ${sale.client.surname} ${sale.client.name} ${sale.client.patr}", style = MaterialTheme.typography.body2)
            Text(text = "Автомобиль: ${sale.auto.model}", style = MaterialTheme.typography.body2)
            Text(text = "Сумма: ${sale.auto.mode.price} рублей", style = MaterialTheme.typography.body2)

            Button(onClick = {
                updateSale.value = true
            }) {
                Text("Редактировать")
            }

            Button(onClick = {
                loadSpinner.value = true
                viewModel.deleteSale(id, navController)
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
                Text("Дата: ${updatedSale.value.date}")
                calendarView()
                    .doOnNext {
                        updatedSale.value = updatedSale.value.copy(date = it)
                    }
                    .subscribe()
            }
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Сотрудник: ")
                val employeesList = viewModel.employeeListStateFlow.value
                val selectedEmployee = dropDownMenu(
                    defaultIndex = employeesList.indexOf(updatedSale.value.employee),
                    list = employeesList
                )
                updatedSale.value = updatedSale.value.copy(employee = selectedEmployee)
            }
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Клиент: ")
                val clientsList = viewModel.clientsListStateFlow.value
                val selectedClient = dropDownMenu(
                    defaultIndex = clientsList.indexOf(updatedSale.value.client),
                    list = clientsList
                )
                updatedSale.value = updatedSale.value.copy(client = selectedClient)
            }
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Автомобиль: ")
                val autoList = viewModel.autoListStateFlow.value
                val selectedAuto = dropDownMenu(
                    defaultIndex = autoList.indexOf(updatedSale.value.auto),
                    list = autoList
                )
                updatedSale.value = updatedSale.value.copy(auto = selectedAuto)
            }
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    viewModel.updateSale(updatedSale.value, navController)
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
