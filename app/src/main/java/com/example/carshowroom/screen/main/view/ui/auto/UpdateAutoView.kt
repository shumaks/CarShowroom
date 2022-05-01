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
import com.example.carshowroom.screen.main.view.ui.NavigationItem
import com.example.carshowroom.screen.main.viewmodel.MainViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun UpdateAutoView(id: Long, navController: NavHostController, viewModel: MainViewModel) {
    val auto = try {
        viewModel.autoListStateFlow.value.first { it.id == id }
    } catch (e: NoSuchElementException) {
        null
    }
    val newAutoImage by viewModel.newAutoImageStateFlow.collectAsState()
    val updatedAuto = remember { mutableStateOf(auto) }
    val loadSpinner = remember { mutableStateOf(false) }
    if (newAutoImage != null) {
        updatedAuto.value = updatedAuto.value?.copy(image = newAutoImage)
    }

    Column {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val image = newAutoImage ?: auto?.image
            image?.let { AutoImage(it) }
            Button(onClick = {
                viewModel.selectPhoto()
            }) {
                Text("Обновить картинку")
            }
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Модель: ")
            updatedAuto.value?.model?.let {
                TextField(
                    value = it,
                    onValueChange = { updatedAuto.value = updatedAuto.value!!.copy(model = it) }
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
                value = updatedAuto.value?.modelYear.toString(),
                onValueChange = {
                    updatedAuto.value = updatedAuto.value?.copy(modelYear = it.toInt())
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
                value = updatedAuto.value?.sits.toString(),
                onValueChange = { updatedAuto.value = updatedAuto.value?.copy(sits = it.toInt()) }
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
                defaultIndex = modeList.indexOf(updatedAuto.value?.mode),
                list = modeList
            )
            updatedAuto.value = updatedAuto.value?.copy(mode = selectedMode)
        }
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                updatedAuto.value?.let { viewModel.updateAuto(it, navController) }
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
