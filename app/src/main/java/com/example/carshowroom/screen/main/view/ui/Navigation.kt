package com.example.carshowroom.screen.main.view.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.carshowroom.screen.main.view.ui.auto.AddAutoView
import com.example.carshowroom.screen.main.view.ui.auto.AutoScreen
import com.example.carshowroom.screen.main.view.ui.auto.AutoView
import com.example.carshowroom.screen.main.view.ui.auto.UpdateAutoView
import com.example.carshowroom.screen.main.view.ui.clients.AddClientView
import com.example.carshowroom.screen.main.view.ui.clients.ClientView
import com.example.carshowroom.screen.main.view.ui.clients.ClientsScreen
import com.example.carshowroom.screen.main.view.ui.employees.AddEmployeeView
import com.example.carshowroom.screen.main.view.ui.employees.EmployeeView
import com.example.carshowroom.screen.main.view.ui.employees.EmployeesScreen
import com.example.carshowroom.screen.main.view.ui.sales.AddSaleView
import com.example.carshowroom.screen.main.view.ui.sales.SaleView
import com.example.carshowroom.screen.main.view.ui.sales.SalesScreen
import com.example.carshowroom.screen.main.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.O)
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
        composable(
            route = "${NavigationRoute.ClientView.value}/{idClient}",
            arguments = listOf(navArgument("idClient") { type = NavType.LongType })
        ) {
            it.arguments?.getLong("idClient")?.let { idClient ->
                ClientView(idClient, navController, viewModel)
            }
        }
        composable(NavigationItem.Employees.route) {
            viewModel.getEmployeesList()
            EmployeesScreen(viewModel, navController)
        }
        composable(
            route = "${NavigationRoute.EmployeeView.value}/{idEmployee}",
            arguments = listOf(navArgument("idEmployee") { type = NavType.LongType })
        ) {
            it.arguments?.getLong("idEmployee")?.let { idEmployee ->
                EmployeeView(idEmployee, navController, viewModel)
            }
        }
        composable(NavigationItem.Auto.route) {
            viewModel.getModeList()
            viewModel.getAutoList()
            AutoScreen(viewModel, navController)
        }
        composable(NavigationRoute.AddAuto.value) {
            viewModel.newAutoImageStateFlow.value = null
            AddAutoView(viewModel, navController)
        }
        composable(NavigationRoute.AddClient.value) {
            AddClientView(viewModel, navController)
        }
        composable(NavigationRoute.AddEmployee.value) {
            AddEmployeeView(viewModel, navController)
        }
        composable(NavigationRoute.AddSale.value) {
            AddSaleView(viewModel, navController)
        }
        composable(NavigationItem.Sales.route) {
            viewModel.getEmployeesList()
            viewModel.getClientsList()
            viewModel.getAutoList()
            viewModel.getSalesList()
            SalesScreen(viewModel, navController)
        }
        composable(
            route = "${NavigationRoute.SaleView.value}/{idSale}",
            arguments = listOf(navArgument("idSale") { type = NavType.LongType })
        ) {
            it.arguments?.getLong("idSale")?.let { idSale ->
                SaleView(idSale, navController, viewModel)
            }
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
