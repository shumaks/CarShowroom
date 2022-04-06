package com.example.carshowroom.utils

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

fun stringToImage(string: String): ImageBitmap {
    val imageBytes = Base64.decode(string, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size).asImageBitmap()
}
