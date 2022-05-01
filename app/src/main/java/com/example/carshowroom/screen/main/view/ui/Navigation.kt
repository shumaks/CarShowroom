package com.example.carshowroom.screen.main.view.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.carshowroom.screen.main.view.ui.auto.AutoScreen
import com.example.carshowroom.screen.main.view.ui.auto.AutoView
import com.example.carshowroom.screen.main.view.ui.auto.UpdateAutoView
import com.example.carshowroom.screen.main.view.ui.clients.ClientsScreen
import com.example.carshowroom.screen.main.view.ui.employees.EmployeesScreen
import com.example.carshowroom.screen.main.view.ui.sales.NavigationRoute
import com.example.carshowroom.screen.main.view.ui.sales.SalesScreen
import com.example.carshowroom.screen.main.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
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
            viewModel.getModeList()
            viewModel.getAutoList()
            AutoScreen(viewModel, navController)
        }
        composable(NavigationItem.Sales.route) {
            viewModel.getSalesList()
            SalesScreen(viewModel)
        }
        composable(
            route = "${NavigationRoute.UpdateAuto.value}/{idAuto}",
            arguments = listOf(navArgument("idAuto") { type = NavType.LongType })
        ) {
            it.arguments?.getLong("idAuto")?.let { idAuto ->
                UpdateAutoView(idAuto, navController, viewModel)
            }
        }
        composable(
            route = "${NavigationRoute.AutoView.value}/{idAuto}",
            arguments = listOf(navArgument("idAuto") { type = NavType.LongType })
        ) {
            viewModel.newAutoImageStateFlow.value = null
            it.arguments?.getLong("idAuto")?.let { idAuto ->
                AutoView(idAuto, navController, viewModel)
            }
        }
    }
}
