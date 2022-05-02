package com.example.carshowroom.screen.main.view.ui.clients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.carshowroom.R
import com.example.carshowroom.screen.main.view.ui.NavigationRoute
import com.example.carshowroom.screen.main.view.ui.auto.AutoListView
import com.example.carshowroom.screen.main.viewmodel.MainViewModel

@Composable
fun ClientsScreen(viewModel: MainViewModel, navController: NavHostController) {
    val clientsList by viewModel.clientsListStateFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.TopStart)
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    navController.navigate(NavigationRoute.AddClient.value)
                }
            ) {
                Text("+")
            }
        }
        LazyColumn(
            modifier = Modifier.padding(bottom = 50.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = clientsList,
                itemContent = {
                    ClientsListView(item = it, navController = navController)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClientsScreenPreview() {
//    ClientsScreen()
}
