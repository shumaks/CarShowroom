package com.example.carshowroom.screen.main.view.ui.clients

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.carshowroom.repo.client.entity.Client
import com.example.carshowroom.screen.main.viewmodel.MainViewModel

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun ClientsListView(item: Client, navController: NavHostController, viewModel: MainViewModel) {
    val selectedClient = remember { mutableStateOf(false) }

    if (!selectedClient.value) {
        Card(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
            elevation = 2.dp,
            backgroundColor = Color.White,
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            onClick = {
                selectedClient.value = true
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = item.surname, style = MaterialTheme.typography.h6)
                Text(text = item.name, style = MaterialTheme.typography.caption)
            }
        }
    } else {
        ClientView(item, navController, viewModel)
    }
}
