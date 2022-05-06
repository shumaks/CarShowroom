package com.example.carshowroom.screen.main.view.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.carshowroom.screen.auth.view.ui.AppTheme
import com.example.carshowroom.screen.main.view.MainActivity
import com.example.carshowroom.screen.main.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreenView(viewModel: MainViewModel, isDirector: Boolean) {
    AppTheme {

        val navController = rememberNavController()

        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomNavigationBar(navController, isDirector) }
        ) {
            Navigation(navController, viewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
//    MainScreenView()
}
