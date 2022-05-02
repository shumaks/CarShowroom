package com.example.carshowroom.screen.main.view.ui.sales

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.Calendar
import java.util.Date

@Composable
fun calendarView(): Observable<String> {
    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int
    val calendar = Calendar.getInstance()
    val dateSubject: PublishSubject<String> = PublishSubject.create()

    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            val dayOfMonth = if (mDayOfMonth < 10) {
                "0$mDayOfMonth"
            } else {
                mDayOfMonth
            }
            val monthOfYear = if (mMonth < 10) {
                "0${mMonth+1}"
            } else {
                mMonth+1
            }
            dateSubject.onNext("$dayOfMonth.$monthOfYear.$mYear")
        }, year, month, day
    )

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = {
            datePickerDialog.show()
        }) {
            Text("Выбрать дату")
        }

        Spacer(modifier = Modifier.size(100.dp))
    }

    return dateSubject
}
