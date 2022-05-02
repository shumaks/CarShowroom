package com.example.carshowroom.screen.main.view.ui.auto

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.carshowroom.repo.auto.entity.Auto
import com.example.carshowroom.repo.auto.entity.Mode
import com.example.carshowroom.repo.client.entity.Client
import com.example.carshowroom.repo.employee.entity.Employee

@Composable
fun <T> dropDownMenu(defaultIndex: Int, list: List<T>): T {
    val expanded = remember { mutableStateOf(false) }
    val items = list.map {
        when(it) {
            is Mode -> it.name
            is Employee -> it.surname
            is Client -> it.surname
            is Auto -> it.model
            else -> TODO()
        }
    }
    val selectedIndex = remember { mutableStateOf(defaultIndex) }
    Box(modifier = Modifier.fillMaxSize().padding(19.dp).wrapContentSize(Alignment.TopStart)) {
        Text(items[selectedIndex.value],modifier = Modifier.fillMaxWidth().clickable(onClick = { expanded.value = true }).background(
            Color.White))
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.fillMaxWidth().background(
                Color.White)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex.value = index
                    expanded.value = false
                }) {
                    Text(text = s)
                }
            }
        }
    }

    return list[selectedIndex.value]
}