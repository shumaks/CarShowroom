package com.example.carshowroom.screen.main.view.ui

import com.example.carshowroom.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Clients : NavigationItem("clients", R.drawable.ic_clients, "Клиенты")
    object Auto : NavigationItem("auto", R.drawable.ic_auto, "Авто")
    object Employees : NavigationItem("employees", R.drawable.ic_employees, "Работники")
    object Sales : NavigationItem("sales", R.drawable.ic_sales, "Заказы")
}
