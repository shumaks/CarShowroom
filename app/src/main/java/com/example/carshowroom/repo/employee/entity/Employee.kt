package com.example.carshowroom.repo.employee.entity

data class Employee(
    val id: Long,
    val surname: String,
    val name: String,
    val patr: String,
    val position: String,
    val address: String,
    val phone: String
)
