package com.example.carshowroom.screen.main.view.ui.sales

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.carshowroom.repo.auto.entity.Auto
import com.example.carshowroom.repo.employee.entity.Employee
import com.example.carshowroom.repo.sale.entity.Sale
import com.example.carshowroom.screen.main.view.ui.auto.dropDownMenu
import com.example.carshowroom.screen.main.viewmodel.MainViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AddSaleView(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val newSale = remember { mutableStateOf(
        Sale(
            id = 0,
            date = "",
            employee = viewModel.employeeListStateFlow.value.first(),
            client =viewModel.clientsListStateFlow.value.first(),
            auto = viewModel.autoListStateFlow.value.first()
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
            Text("Сотрудник: ")
            val employeesList = viewModel.employeeListStateFlow.value
            val selectedEmployee = dropDownMenu(
                defaultIndex = 0,
                list = employeesList
            )
            newSale.value = newSale.value.copy(employee = selectedEmployee)
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
                defaultIndex = 0,
                list = clientsList
            )
            newSale.value = newSale.value.copy(client = selectedClient)
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Авто: ")
            val autoList = viewModel.autoListStateFlow.value
            val selectedAuto = dropDownMenu(
                defaultIndex = 0,
                list = autoList
            )
            newSale.value = newSale.value.copy(auto = selectedAuto)
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                val currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                newSale.value = newSale.value.copy(date = currentDate)
                viewModel.addSale(newSale.value, navController)
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
