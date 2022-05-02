package com.example.carshowroom.screen.main.view.ui

sealed class NavigationRoute(val value: String) {

    object UpdateAuto : NavigationRoute("updateAuto")
    object AddAuto : NavigationRoute("addAuto")
    object AddClient : NavigationRoute("addClient")
    object AddSale : NavigationRoute("addSale")
    object AddEmployee : NavigationRoute("addEmployee")
    object AutoView : NavigationRoute("autoView")
    object ClientView : NavigationRoute("clientView")
    object EmployeeView : NavigationRoute("employeeView")
    object SaleView : NavigationRoute("saleView")
}
