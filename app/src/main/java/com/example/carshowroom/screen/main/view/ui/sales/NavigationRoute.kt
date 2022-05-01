package com.example.carshowroom.screen.main.view.ui.sales

sealed class NavigationRoute(val value: String) {

    object UpdateAuto : NavigationRoute("updateAuto")
    object AutoView : NavigationRoute("autoView")
}
