package com.example.carshowroom.screen.main.view.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carshowroom.screen.main.view.ui.auto.AutoScreen
import com.example.carshowroom.screen.main.view.ui.clients.ClientsScreen
import com.example.carshowroom.screen.main.view.ui.employees.EmployeesScreen
import com.example.carshowroom.screen.main.view.ui.sales.SalesScreen
import com.example.carshowroom.screen.main.viewmodel.MainViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(navController, startDestination = NavigationItem.Auto.route) {
        composable(NavigationItem.Clients.route) {
            viewModel.getClientsList()
            ClientsScreen(viewModel, navController)
        }
        composable(NavigationItem.Employees.route) {
            viewModel.getEmployeesList()
            EmployeesScreen(viewModel)
        }
        composable(NavigationItem.Auto.route) {
            viewModel.getAutoList()
            AutoScreen(viewModel)
        }
        composable(NavigationItem.Sales.route) {
            viewModel.getSalesList()
            SalesScreen(viewModel)
        }
    }
}
