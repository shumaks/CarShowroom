package com.example.carshowroom.service.retrofit

import com.example.carshowroom.repo.auto.entity.Auto
import com.example.carshowroom.repo.client.entity.Client
import com.example.carshowroom.repo.employee.entity.Employee
import com.example.carshowroom.repo.sale.entity.Sale
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://192.168.100.9:8080/"

interface APIService {

    @GET("auto/getAll")
    fun getAuto(): Single<List<Auto>>

    @GET("clients/gelAll")
    fun getClients(): Single<List<Client>>

    @GET("sales/getAll")
    fun getSales(): Single<List<Sale>>

    @GET("employees/getAll")
    fun getEmployees(): Single<List<Employee>>

    companion object {
        var retrofitService: APIService? = null
        fun getInstance() : APIService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
                retrofitService = retrofit.create(APIService::class.java)
            }
            return retrofitService!!
        }
    }
}