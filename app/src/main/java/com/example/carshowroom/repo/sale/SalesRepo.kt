package com.example.carshowroom.repo.sale

import com.example.carshowroom.service.retrofit.APIService

class SalesRepo(
    private val apiService: APIService
) {

    fun getSalesList() = apiService.getSales()
}
