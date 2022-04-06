package com.example.carshowroom.repo.auto.entity

data class Mode(
    val id: Long,
    val name: String,
    val maxSpeed: Int,
    val accelerationTime: Double,
    val engineVolume: Double,
    val gasMileage: Double,
    val price: Int
)
