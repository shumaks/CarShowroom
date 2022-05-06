package com.example.carshowroom.screen.auth.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.carshowroom.repo.user.entity.User
import com.example.carshowroom.screen.auth.router.AuthRouter
import com.example.carshowroom.service.retrofit.APIService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow

class AuthViewModel(
    private val authRouter: AuthRouter,
    private val apiService: APIService,
    private val context: Context
) : ViewModel() {

    val loadingStateFlow = MutableStateFlow(false)
    val loginViewStateFlow = MutableStateFlow(true)

    fun login(login: String, password: String) {
        loadingStateFlow.value = true
        apiService.loginUser(User(login, password, ""))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<String>() {
                override fun onSuccess(accessRights: String) {
                    Log.d("AndroidRuntime", accessRights)
                    loadingStateFlow.value = false
                    when (accessRights) {
                        "manager" -> authRouter.goToMainScreen(false)
                        "director" -> authRouter.goToMainScreen(true)
                        else -> Toast.makeText(
                            context,
                            "Логин или пароль введёны неверно.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onError(@NonNull e: Throwable) {
                    Log.d("AndroidRuntime", e.toString())
                }
            })
    }

    fun showLoginErrorMessage() {
        Toast.makeText(
            context,
            "Пожалуйста, введите логин и пароль!",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun registerUser(login: String, password: String) {
        loadingStateFlow.value = true
        apiService.addUser(User(login, password, "manager"))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Boolean>() {
                override fun onSuccess(isRegistered: Boolean) {
                    Log.d("AndroidRuntime", isRegistered.toString())
                    loadingStateFlow.value = false
                    if (isRegistered) {
                        loginViewStateFlow.value = true
                    } else {
                        Toast.makeText(
                            context,
                            "Пользователь с таким логином уже существует.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onError(@NonNull e: Throwable) {
                    Log.d("AndroidRuntime", e.toString())
                }
            })
    }
}
