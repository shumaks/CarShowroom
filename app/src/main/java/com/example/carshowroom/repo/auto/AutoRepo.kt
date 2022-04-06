package com.example.carshowroom.repo.auto

import com.example.carshowroom.service.retrofit.APIService

class AutoRepo(
    private val apiService: APIService
) {

    fun getAutoList() = apiService.getAuto()
}
