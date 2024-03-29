package com.example.carshowroom

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.carshowroom.repo.auto.AutoRepo
import com.example.carshowroom.repo.client.ClientsRepo
import com.example.carshowroom.repo.employee.EmployeesRepo
import com.example.carshowroom.repo.mode.ModeRepo
import com.example.carshowroom.repo.sale.SalesRepo
import com.example.carshowroom.screen.auth.router.AuthRouter
import com.example.carshowroom.screen.auth.viewmodel.AuthViewModel
import com.example.carshowroom.screen.main.view.MainActivity
import com.example.carshowroom.screen.main.viewmodel.MainViewModel
import com.example.carshowroom.service.file.FileService
import com.example.carshowroom.service.retrofit.APIService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

const val APP_TAG = "AndroidRuntime"

class App : Application() {

    private val viewModelModule = module {
        viewModel { AuthViewModel(get(), get(), get()) }
        viewModel { MainViewModel(get(), get(), get(), get(), get(), get()) }
    }

    private val routerModule = module {
        single { AuthRouter(get()) }
    }

    private val serviceModule = module {
        single { APIService.getInstance() }
        single { FileService(get()) }
    }

    private val repoModule = module {
        single { AutoRepo(get()) }
        single { ClientsRepo(get()) }
        single { SalesRepo(get()) }
        single { EmployeesRepo(get()) }
        single { ModeRepo(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        context = applicationContext

        startKoin {
            androidContext(this@App)
            modules(listOf(viewModelModule, routerModule, serviceModule, repoModule))
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: App private set
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context private set
        @SuppressLint("StaticFieldLeak")
        lateinit var mainActivity: MainActivity
    }
}
