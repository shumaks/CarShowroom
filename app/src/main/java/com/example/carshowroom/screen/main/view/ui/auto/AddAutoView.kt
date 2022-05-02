package com.example.carshowroom.screen.main.view.ui.auto

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carshowroom.repo.auto.entity.Auto
import com.example.carshowroom.screen.main.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AddAutoView(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val newAutoImage by viewModel.newAutoImageStateFlow.collectAsState()
    val newAuto = remember { mutableStateOf(
        Auto(
            id = 0,
            model = "",
            sits = 0,
            modelYear = 0,
            image = null,
            mode = viewModel.modeListStateFlow.value.first()
        )
    ) }
    val loadSpinner = remember { mutableStateOf(false) }
    if (newAutoImage != null) {
        newAuto.value = newAuto.value.copy(image = newAutoImage)
    }

    Column {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            newAutoImage?.let { AutoImage(it) }
            Button(onClick = {
                viewModel.selectPhoto()
            }) {
                Text("Добавить картинку")
            }
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Модель: ")
            newAuto.value.model.let {
                TextField(
                    value = it,
                    onValueChange = { newAuto.value = newAuto.value.copy(model = it) }
                )
            }
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Год выпуска:  ")
            TextField(
                value = newAuto.value.modelYear.toString(),
                onValueChange = {
                    newAuto.value = newAuto.value.copy(modelYear = try { it.toInt() } catch(e: NumberFormatException) { 0 })
                }
            )
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Количество мест: ")
            TextField(
                value = newAuto.value.sits.toString(),
                onValueChange = { newAuto.value = newAuto.value.copy(sits = try { it.toInt() } catch(e: NumberFormatException) { 0 }) }
            )
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text("Модификация: ")
            val modeList = viewModel.modeListStateFlow.value
            val selectedMode = dropDownMenu(
                defaultIndex = modeList.indexOf(newAuto.value.mode),
                list = modeList
            )
            newAuto.value = newAuto.value.copy(mode = selectedMode)
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                viewModel.addAuto(newAuto.value, navController)
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
