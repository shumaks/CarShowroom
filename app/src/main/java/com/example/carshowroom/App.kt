package com.example.carshowroom

import android.app.Application
import android.content.Context
import com.example.carshowroom.screen.auth.viewmodel.AuthViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    private val viewModelModule = module {
        viewModel { AuthViewModel() }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        context = applicationContext

        startKoin {
            androidContext(this@App)
            modules(listOf(viewModelModule))
        }
    }

    companion object {
        lateinit var instance: App private set
        lateinit var context: Context private set
    }
}
