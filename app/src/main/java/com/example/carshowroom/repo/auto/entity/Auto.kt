package com.example.carshowroom.repo.auto.entity

import com.example.carshowroom.repo.mode.entity.Mode

data class Auto(
    var id: Long,
    val model: String,
    val sits: Int,
    val modelYear: Int,
    val image: String?,
    val mode: Mode
)
