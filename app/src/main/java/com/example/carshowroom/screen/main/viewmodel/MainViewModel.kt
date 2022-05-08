package com.example.carshowroom.screen.main.viewmodel

import android.content.Intent
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.carshowroom.App
import com.example.carshowroom.repo.auto.AutoRepo
import com.example.carshowroom.repo.auto.entity.Auto
import com.example.carshowroom.repo.mode.entity.Mode
import com.example.carshowroom.repo.client.ClientsRepo
import com.example.carshowroom.repo.client.entity.Client
import com.example.carshowroom.repo.employee.EmployeesRepo
import com.example.carshowroom.repo.employee.entity.Employee
import com.example.carshowroom.repo.mode.ModeRepo
import com.example.carshowroom.repo.sale.SalesRepo
import com.example.carshowroom.repo.sale.entity.Sale
import com.example.carshowroom.screen.main.view.ui.NavigationItem
import com.example.carshowroom.service.file.FileService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel(
    private val autoRepo: AutoRepo,
    private val clientsRepo: ClientsRepo,
    private val salesRepo: SalesRepo,
    private val employeesRepo: EmployeesRepo,
    private val modeRepo: ModeRepo,
    private val fileService: FileService
) : ViewModel() {

    val autoListStateFlow = MutableStateFlow<List<Auto>>(emptyList())
    val modeListStateFlow = MutableStateFlow<List<Mode>>(emptyList())
    val clientsListStateFlow = MutableStateFlow<List<Client>>(emptyList())
    val salesListStateFlow = MutableStateFlow<List<Sale>>(emptyList())
    val employeeListStateFlow = MutableStateFlow<List<Employee>>(emptyList())
    val newAutoImageStateFlow = MutableStateFlow<String?>(null)

    fun createPdfFile(sale: Sale) = fileService.createPdfFile(sale)

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

    fun updateAuto(auto: Auto, navController: NavHostController) = autoRepo.updateAutoList(auto)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Auto>() {
            override fun onSuccess(auto: Auto) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Auto.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

    fun addAuto(auto: Auto, navController: NavHostController) = autoRepo.addAuto(auto)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Auto>() {
            override fun onSuccess(auto: Auto) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Auto.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

    fun getModeList() {
        modeRepo.getModeList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<List<Mode>>() {
                override fun onSuccess(list: List<Mode>) {
                    modeListStateFlow.value = list
                }

                override fun onError(@NonNull e: Throwable) {
                    Log.d("Error", e.toString())
                }
            })
    }

    fun updateMode(mode: Mode, navController: NavHostController) = modeRepo.updateMode(mode)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Mode>() {
            override fun onSuccess(mode: Mode) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Auto.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

    fun addMode(mode: Mode, navController: NavHostController) = modeRepo.addMode(mode)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Mode>() {
            override fun onSuccess(mode: Mode) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Auto.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

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

    fun updateClient(client: Client, navController: NavHostController) = clientsRepo.updateClientsList(client)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Client>() {
            override fun onSuccess(client: Client) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Clients.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

    fun addClient(client: Client, navController: NavHostController) = clientsRepo.addClient(client)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Client>() {
            override fun onSuccess(client: Client) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Clients.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

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

    fun updateSale(sale: Sale, navController: NavHostController) = salesRepo.updateSalesList(sale)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Sale>() {
            override fun onSuccess(sale: Sale) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Sales.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

    fun addSale(sale: Sale, navController: NavHostController) = salesRepo.addSale(sale)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Sale>() {
            override fun onSuccess(sale: Sale) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Sales.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

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

    fun updateEmployee(employee: Employee, navController: NavHostController) = employeesRepo.updateEmployeesList(employee)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Employee>() {
            override fun onSuccess(employee: Employee) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Employees.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

    fun addEmployee(employee: Employee, navController: NavHostController) = employeesRepo.addEmployee(employee)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Employee>() {
            override fun onSuccess(employee: Employee) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Employees.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

    fun deleteEmployee(id: Long, navController: NavHostController) = employeesRepo.deleteEmployee(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Long>() {
            override fun onSuccess(id: Long) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Employees.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

    fun deleteClient(id: Long, navController: NavHostController) = clientsRepo.deleteClient(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Long>() {
            override fun onSuccess(id: Long) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Clients.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

    fun deleteAuto(id: Long, navController: NavHostController) = autoRepo.deleteAuto(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Long>() {
            override fun onSuccess(id: Long) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Auto.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

    fun deleteMode(id: Long, navController: NavHostController) = modeRepo.deleteMode(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Long>() {
            override fun onSuccess(id: Long) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Auto.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

    fun deleteSale(id: Long, navController: NavHostController) = salesRepo.deleteSale(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : DisposableSingleObserver<Long>() {
            override fun onSuccess(id: Long) {
                Log.d("Success", "")
                navController.navigate(NavigationItem.Sales.route)
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d("Error", e.toString())
            }
        })

    fun selectPhoto() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        ActivityCompat.startActivityForResult(App.mainActivity, photoPickerIntent, 200, null)
    }
}
