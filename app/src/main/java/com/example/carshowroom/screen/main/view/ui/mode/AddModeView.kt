package com.example.carshowroom.screen.main.view.ui.mode

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
import com.example.carshowroom.repo.auto.entity.Auto
import com.example.carshowroom.repo.mode.entity.Mode
import com.example.carshowroom.screen.main.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AddModeView(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val newMode = remember { mutableStateOf(
        Mode(
            id = 0,
            name = "",
            maxSpeed = 0,
            accelerationTime = 0.toDouble(),
            engineVolume = 0.toDouble(),
            gasMileage = 0.toDouble(),
            price = 0
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
            Text("Название: ")
            newMode.value.name.let {
                TextField(
                    value = it,
                    onValueChange = { newMode.value = newMode.value.copy(name = it) }
                )
            }
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Максимальная скорость:  ")
            TextField(
                value = newMode.value.maxSpeed.toString(),
                onValueChange = {
                    newMode.value = newMode.value.copy(maxSpeed = try { it.toInt() } catch(e: NumberFormatException) { 0 })
                }
            )
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Разгон до 100 км/ч:  ")
            TextField(
                value = newMode.value.accelerationTime.toString(),
                onValueChange = {
                    newMode.value = newMode.value.copy(accelerationTime = try { it.toDouble() } catch(e: NumberFormatException) { 0.toDouble() })
                }
            )
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Объем двигателя:  ")
            TextField(
                value = newMode.value.engineVolume.toString(),
                onValueChange = {
                    newMode.value = newMode.value.copy(engineVolume = try { it.toDouble() } catch(e: NumberFormatException) { 0.toDouble() })
                }
            )
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Расход топлива:  ")
            TextField(
                value = newMode.value.gasMileage.toString(),
                onValueChange = {
                    newMode.value = newMode.value.copy(gasMileage = try { it.toDouble() } catch(e: NumberFormatException) { 0.toDouble() })
                }
            )
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Цена:  ")
            TextField(
                value = newMode.value.price.toString(),
                onValueChange = {
                    newMode.value = newMode.value.copy(price = try { it.toInt() } catch(e: NumberFormatException) { 0 })
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
                viewModel.addMode(newMode.value, navController)
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
