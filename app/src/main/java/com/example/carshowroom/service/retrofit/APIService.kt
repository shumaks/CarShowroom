package com.example.carshowroom.service.retrofit

import com.example.carshowroom.repo.auto.entity.Auto
import com.example.carshowroom.repo.auto.entity.Mode
import com.example.carshowroom.repo.client.entity.Client
import com.example.carshowroom.repo.employee.entity.Employee
import com.example.carshowroom.repo.sale.entity.Sale
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "http://192.168.100.2:8080/"

interface APIService {

    @GET("auto/getAll")
    fun getAuto(): Single<List<Auto>>

    @POST("auto/updateAuto")
    fun updateAuto(@Body auto: Auto): Single<Auto>

    @POST("auto/addAuto")
    fun addAuto(@Body auto: Auto): Single<Auto>

    @POST("auto/deleteAuto")
    fun deleteAuto(@Body id: Long): Single<Long>

    @POST("clients/deleteClient")
    fun deleteClient(@Body id: Long): Single<Long>

    @POST("sales/deleteSale")
    fun deleteSale(@Body id: Long): Single<Long>

    @POST("employees/deleteEmployee")
    fun deleteEmployee(@Body id: Long): Single<Long>

    @GET("mode/getAll")
    fun getMode(): Single<List<Mode>>

    @GET("clients/getAll")
    fun getClients(): Single<List<Client>>

    @GET("sales/getAll")
    fun getSales(): Single<List<Sale>>

    @POST("sales/updateSale")
    fun updateSale(@Body sale: Sale): Single<Sale>

    @POST("sales/addSale")
    fun addSale(@Body sale: Sale): Single<Sale>

    @GET("employees/getAll")
    fun getEmployees(): Single<List<Employee>>

    @POST("employees/updateEmployee")
    fun updateEmployee(@Body employee: Employee): Single<Employee>

    @POST("employees/addEmployee")
    fun addEmployee(@Body employee: Employee): Single<Employee>

    @POST("clients/addClient")
    fun addClient(@Body client: Client): Single<Client>

    @POST("clients/updateClient")
    fun updateClient(@Body client: Client): Single<Client>

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
