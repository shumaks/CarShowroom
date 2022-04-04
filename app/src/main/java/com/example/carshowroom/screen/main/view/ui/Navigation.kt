package com.example.carshowroom.screen.main.view.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Auto.route) {
        composable(NavigationItem.Clients.route) {
            ClientsScreen()
        }
        composable(NavigationItem.Employees.route) {
            EmployeesScreen()
        }
        composable(NavigationItem.Auto.route) {
            AutoScreen()
        }
        composable(NavigationItem.Sales.route) {
            SalesScreen()
        }
    }
}
