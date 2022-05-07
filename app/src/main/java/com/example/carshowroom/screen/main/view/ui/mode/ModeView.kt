package com.example.carshowroom.screen.main.view.ui.mode


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carshowroom.screen.main.view.ui.NavigationRoute
import com.example.carshowroom.screen.main.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ModeView(id: Long, navController: NavHostController, viewModel: MainViewModel) {
    val mode = viewModel.modeListStateFlow.value.first { it.id == id }
    val loadSpinner = remember { mutableStateOf(false) }

    Column {
        Text(text = mode.name, style = typography.h6)
        Text(
            text = "Разгоняется до 100 км/ч за ${mode.accelerationTime} секунд",
            style = typography.body2
        )
        Text(text = "Объем двигателя: ${mode.engineVolume} литров", style = typography.body2)
        Text(
            text = "Расход топлива: ${mode.gasMileage} литров на километр",
            style = typography.body2
        )
        Text(
            text = "Расход топлива: ${mode.gasMileage} литров на 100 километров",
            style = typography.body2
        )
        Text(text = "Максимальная скорость: ${mode.maxSpeed} км/ч", style = typography.body2)
        Text(text = "Цена: ${mode.price} рублей", style = typography.body2)

        Button(onClick = {
            navController.navigate("${NavigationRoute.UpdateMode.value}/${mode.id}")
        }) {
            Text("Редактировать")
        }

        Button(onClick = {
            loadSpinner.value = true
            viewModel.deleteMode(id, navController)

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
}
