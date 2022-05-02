package com.example.carshowroom.repo.sale

import com.example.carshowroom.repo.sale.entity.Sale
import com.example.carshowroom.service.retrofit.APIService

class SalesRepo(
    private val apiService: APIService
) {

    fun getSalesList() = apiService.getSales()

    fun updateSalesList(sale: Sale) = apiService.updateSale(sale)

    fun addSale(sale: Sale) = apiService.addSale(sale)

    fun deleteSale(id: Long) = apiService.deleteSale(id)
}
