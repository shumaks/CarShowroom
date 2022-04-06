package com.example.carshowroom.screen.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.carshowroom.repo.auto.AutoRepo
import com.example.carshowroom.repo.auto.entity.Auto
import com.example.carshowroom.repo.client.ClientsRepo
import com.example.carshowroom.repo.client.entity.Client
import com.example.carshowroom.repo.employee.EmployeesRepo
import com.example.carshowroom.repo.employee.entity.Employee
import com.example.carshowroom.repo.sale.SalesRepo
import com.example.carshowroom.repo.sale.entity.Sale
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(
    private val autoRepo: AutoRepo,
    private val clientsRepo: ClientsRepo,
    private val salesRepo: SalesRepo,
    private val employeesRepo: EmployeesRepo
) : ViewModel() {

    val autoListStateFlow = MutableStateFlow<List<Auto>>(emptyList())
    val clientsListStateFlow = MutableStateFlow<List<Client>>(emptyList())
    val salesListStateFlow = MutableStateFlow<List<Sale>>(emptyList())
    val employeeListStateFlow = MutableStateFlow<List<Employee>>(emptyList())

    fun getAutoList() {
        autoRepo.getAutoList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Auto>>() {
                override fun onSuccess(list: List<Auto>) {
                    autoListStateFlow.value = list
                }

                override fun onError(@NonNull e: Throwable) {
                    Log.d("Error", e.toString())
                }
            })
    }

    fun getClientsList() {
        clientsRepo.getClientsList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Client>>() {
                override fun onSuccess(list: List<Client>) {
                    clientsListStateFlow.value = list
                }

                override fun onError(@NonNull e: Throwable) {
                    Log.d("Error", e.toString())
                }
            })
    }

    fun getSalesList() {
        salesRepo.getSalesList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Sale>>() {
                override fun onSuccess(list: List<Sale>) {
                    salesListStateFlow.value = list
                }

                override fun onError(@NonNull e: Throwable) {
                    Log.d("Error", e.toString())
                }
            })
    }

    fun getEmployeesList() {
        employeesRepo.getEmployeesList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Employee>>() {
                override fun onSuccess(list: List<Employee>) {
                    employeeListStateFlow.value = list
                }

                override fun onError(@NonNull e: Throwable) {
                    Log.d("Error", e.toString())
                }
            })
    }
}
