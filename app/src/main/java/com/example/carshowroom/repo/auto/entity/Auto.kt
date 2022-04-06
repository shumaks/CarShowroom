package com.example.carshowroom.repo.auto.entity

data class Auto(
    var id: Long,
    val model: String,
    val sits: Int,
    val modelYear: Int,
    val image: String?,
    val mode: Mode
)