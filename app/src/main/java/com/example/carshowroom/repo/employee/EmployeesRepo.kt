package com.example.carshowroom.repo.employee

import com.example.carshowroom.service.retrofit.APIService

class EmployeesRepo(
    private val apiService: APIService
) {

    fun getEmployeesList() = apiService.getEmployees()
}
