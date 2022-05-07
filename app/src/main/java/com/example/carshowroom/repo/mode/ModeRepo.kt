package com.example.carshowroom.repo.mode

import com.example.carshowroom.repo.mode.entity.Mode
import com.example.carshowroom.service.retrofit.APIService

class ModeRepo(
    private val apiService: APIService
) {

    fun getModeList() = apiService.getMode()

    fun addMode(mode: Mode) = apiService.addMode(mode)

    fun updateMode(mode: Mode) = apiService.updateMode(mode)

    fun deleteMode(id: Long) = apiService.deleteMode(id)
}