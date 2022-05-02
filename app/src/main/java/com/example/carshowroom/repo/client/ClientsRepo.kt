package com.example.carshowroom.repo.client

import com.example.carshowroom.repo.client.entity.Client
import com.example.carshowroom.service.retrofit.APIService

class ClientsRepo(
    private val apiService: APIService
) {

    fun getClientsList() = apiService.getClients()

    fun updateClientsList(client: Client) = apiService.updateClient(client)

    fun addClient(client: Client) = apiService.addClient(client)

    fun deleteClient(id: Long) = apiService.deleteClient(id)
}
