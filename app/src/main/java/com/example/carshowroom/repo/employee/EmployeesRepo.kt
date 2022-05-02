package com.example.carshowroom.repo.employee

import com.example.carshowroom.repo.employee.entity.Employee
import com.example.carshowroom.service.retrofit.APIService

class EmployeesRepo(
    private val apiService: APIService
) {

    fun getEmployeesList() = apiService.getEmployees()

    fun updateEmployeesList(employee: Employee) = apiService.updateEmployee(employee)

    fun addEmployee(employee: Employee) = apiService.addEmployee(employee)

    fun deleteEmployee(id: Long) = apiService.deleteEmployee(id)
}
