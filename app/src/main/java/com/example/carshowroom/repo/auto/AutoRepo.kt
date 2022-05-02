package com.example.carshowroom.repo.auto

import com.example.carshowroom.repo.auto.entity.Auto
import com.example.carshowroom.service.retrofit.APIService

class AutoRepo(
    private val apiService: APIService
) {

    fun getAutoList() = apiService.getAuto()

    fun updateAutoList(auto: Auto) = apiService.updateAuto(auto)

    fun getModeList() = apiService.getMode()

    fun addAuto(auto: Auto) = apiService.addAuto(auto)

    fun deleteAuto(id: Long) = apiService.deleteAuto(id)
}
