package com.example.carshowroom.screen.main.view.ui.auto


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
fun AutoView(id: Long, navController: NavHostController, viewModel: MainViewModel) {
    val auto = viewModel.autoListStateFlow.value.first { it.id == id }
    val loadSpinner = remember { mutableStateOf(false) }

    Column {
        auto.image?.let { if (it != "null") { AutoImage(it) } }
        Text(text = auto.mode.name, style = typography.h6)
        Text(text = "${auto.modelYear} г.", style = typography.body2)
        Text(text = "${auto.sits} мест", style = typography.body2)
        Text(
            text = "Разгоняется до 100 км/ч за ${auto.mode.accelerationTime} секунд",
            style = typography.body2
        )
        Text(text = "Объем двигателя: ${auto.mode.engineVolume} литров", style = typography.body2)
        Text(
            text = "Расход топлива: ${auto.mode.gasMileage} литров на километр",
            style = typography.body2
        )
        Text(
            text = "Расход топлива: ${auto.mode.gasMileage} литров на 100 километров",
            style = typography.body2
        )
        Text(text = "Максимальная скорость: ${auto.mode.maxSpeed} км/ч", style = typography.body2)
        Text(text = "Цена: ${auto.mode.price} рублей", style = typography.body2)

        Button(onClick = {
            navController.navigate("${NavigationRoute.UpdateAuto.value}/${auto.id}")
        }) {
            Text("Редактировать")
        }

        Button(onClick = {
            loadSpinner.value = true
            viewModel.deleteAuto(id, navController)

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
