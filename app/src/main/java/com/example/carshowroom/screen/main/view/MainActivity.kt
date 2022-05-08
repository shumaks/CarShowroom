package com.example.carshowroom.screen.main.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.carshowroom.App
import com.example.carshowroom.screen.main.view.ui.MainScreenView
import com.example.carshowroom.screen.main.viewmodel.MainViewModel
import com.example.carshowroom.utils.imageToString
import java.io.FileNotFoundException
import java.io.InputStream
import org.koin.androidx.compose.getViewModel

private const val MAX_IMAGE_LENGTH = 4_000_000

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.mainActivity = this
        verifyStoragePermissions()
        supportActionBar?.hide()

        val isDirector = intent.getBooleanExtra("isDirector", false)

        setContent {
            viewModel = getViewModel()
            MainScreenView(viewModel, isDirector)
        }
    }

    private fun verifyStoragePermissions() {
        val readStoragePermission = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        val writeStoragePermission = ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (readStoragePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                200
            )
        }
        if (writeStoragePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                200
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            200 -> if (resultCode == RESULT_OK) {
                try {
                    val imageStream: InputStream? = data?.data?.let {
                        contentResolver.openInputStream(it)
                    }
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    val stringImage = imageToString(selectedImage)
                    if (stringImage.length < MAX_IMAGE_LENGTH) {
                        viewModel.newAutoImageStateFlow.value = stringImage
                    } else {
                        Toast.makeText(applicationContext, "Картинка слишком большая! Пожалуйста, выберите картинку меньшего размера.",  Toast.LENGTH_SHORT).show()
                    }
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
