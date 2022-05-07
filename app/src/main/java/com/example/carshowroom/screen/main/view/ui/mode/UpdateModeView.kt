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
import com.example.carshowroom.screen.main.viewmodel.MainViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun UpdateModeView(id: Long, navController: NavHostController, viewModel: MainViewModel) {
    val mode = try {
        viewModel.modeListStateFlow.value.first { it.id == id }
    } catch (e: NoSuchElementException) {
        null
    }
    val updatedMode = remember { mutableStateOf(mode) }
    val loadSpinner = remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Название: ")
            updatedMode.value?.name?.let {
                TextField(
                    value = it,
                    onValueChange = { updatedMode.value = updatedMode.value!!.copy(name = it) }
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
                value = updatedMode.value?.maxSpeed.toString(),
                onValueChange = {
                    updatedMode.value = updatedMode.value?.copy(
                        maxSpeed = try {
                            it.toInt()
                        } catch (e: NumberFormatException) {
                            0
                        }
                    )
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
                value = updatedMode.value?.accelerationTime.toString(),
                onValueChange = {
                    updatedMode.value = updatedMode.value?.copy(
                        accelerationTime = try {
                            it.toDouble()
                        } catch (e: NumberFormatException) {
                            0.toDouble()
                        }
                    )
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
                value = updatedMode.value?.engineVolume.toString(),
                onValueChange = {
                    updatedMode.value = updatedMode.value?.copy(
                        engineVolume = try {
                            it.toDouble()
                        } catch (e: NumberFormatException) {
                            0.toDouble()
                        }
                    )
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
                value = updatedMode.value?.gasMileage.toString(),
                onValueChange = {
                    updatedMode.value = updatedMode.value?.copy(
                        gasMileage = try {
                            it.toDouble()
                        } catch (e: NumberFormatException) {
                            0.toDouble()
                        }
                    )
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
                value = updatedMode.value?.price.toString(),
                onValueChange = {
                    updatedMode.value = updatedMode.value?.copy(
                        price = try {
                            it.toInt()
                        } catch (e: NumberFormatException) {
                            0
                        }
                    )
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
                updatedMode.value?.let { viewModel.updateMode(it, navController) }
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
