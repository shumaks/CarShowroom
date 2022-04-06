package com.example.carshowroom.repo.sale.entity

import com.example.carshowroom.repo.auto.entity.Auto
import com.example.carshowroom.repo.client.entity.Client
import com.example.carshowroom.repo.employee.entity.Employee

data class Sale(
    val id: Long,
    val date: String,
    val employee: Employee,
    val client: Client,
    val auto: Auto
)
