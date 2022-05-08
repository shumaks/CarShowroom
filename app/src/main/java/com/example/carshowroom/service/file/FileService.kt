package com.example.carshowroom.service.file

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.media.MediaScannerConnection
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.carshowroom.APP_TAG
import com.example.carshowroom.R
import com.example.carshowroom.repo.sale.entity.Sale
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FileService(
    private val context: Context
) {

    fun createPdfFile(sale: Sale) {

        val pageHeight = 1120
        val pageWidth = 792

        val pdfDocument = PdfDocument()
        val title = Paint()
        val pageInfo = PageInfo.Builder(pageWidth, pageHeight, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas = page.canvas

        title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        title.textSize = 24f
        title.color = ContextCompat.getColor(context, R.color.black)
        title.textAlign = Paint.Align.CENTER

        canvas.drawText("ДОГОВОР КУПЛИ-ПРОДАЖИ ТРАНСПОРТНОГО СРЕДСТВА", 396f, 100f, title)

        title.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
        title.color = ContextCompat.getColor(context, R.color.black)
        title.textSize = 20f
        title.textAlign = Paint.Align.LEFT

        canvas.drawText("Республика Беларусь, г. Минск                                                                     ${sale.date}", 30f, 150f, title)
        canvas.drawText("${sale.employee.surname} ${sale.employee.name} ${sale.employee.patr}, именуемый в дальнейшем \"Продавец\"", 30f, 180f, title)
        canvas.drawText("и ${sale.client.surname} ${sale.client.name} ${sale.client.patr}, именуемый в дальнейшем \"Покупатель\".", 30f, 210f, title)
        canvas.drawText("заключили между собой договор о нижеследующем:", 30f, 240f, title)
        canvas.drawText("1. \"Продавец\" продает \"Покупателю\" ${sale.auto.model} марки и модели ${sale.auto.mode.name},", 30f, 270f, title)
        canvas.drawText("выпуска ${sale.auto.modelYear} года.", 30f, 300f, title)
        canvas.drawText("2. Принадлежность \"Продавцу\" указанного транспортного средства", 30f, 330f, title)
        canvas.drawText("подтверждается свидетельством о регистрации транспортного средства", 30f, 360f, title)
        canvas.drawText("(техническим паспортом).", 30f, 390f, title)
        canvas.drawText("3. Стоимость транспортного средства составляет: ${sale.auto.mode.price} белорусских рублей.", 30f, 420f, title)
        canvas.drawText("4. Оплата транспортного средства производится ${sale.date}, наличными.", 30f, 450f, title)
        canvas.drawText("5. Права, обязанности и ответственность сторон по договору определяются", 30f, 480f, title)
        canvas.drawText("законодательством Республики Беларусь.", 30f, 510f, title)
        canvas.drawText("6. \"Продавец\" удостоверяет, что до подписания настоящего договора", 30f, 540f, title)
        canvas.drawText("транспортное средство никому не подарено, не продано, не заложено, в аренде", 30f, 570f, title)
        canvas.drawText("и под арестом не состоит, судебного спора о нем не имеется, свободно", 30f, 600f, title)
        canvas.drawText("от любых прав и притязаний со стороны третьих лиц.", 30f, 630f, title)
        canvas.drawText("7. Иные условия договора: ____________________________________________", 30f, 660f, title)
        canvas.drawText("8. Настоящий договор составлен в трех экземплярах, один из которых остается", 30f, 690f, title)
        canvas.drawText("у продавца, другой - у покупателя, третий - у РЭП ГАИ.", 30f, 720f, title)
        canvas.drawText("Адреса и реквизиты сторон:", 300f, 750f, title)
        canvas.drawText("Продавец: ${sale.employee.surname} ${sale.employee.name.first()}. ${sale.employee.patr.first()}.  ________                  Покупатель: ${sale.client.surname} ${sale.client.name.first()}. ${sale.client.patr.first()}.  ________", 30f, 780f, title)

        pdfDocument.finishPage(page)

        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)?.path
        val file = File(path, "sale-contract-${sale.id}.pdf")

        try {
            pdfDocument.writeTo(FileOutputStream(file))

            Toast.makeText(
                context,
                "Договор успешно сохранен.",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: IOException) {
            Log.e(APP_TAG, e.toString())
            Toast.makeText(
                context,
                "Произошла ошибка! Пожалуйста, повторите ещё раз.",
                Toast.LENGTH_SHORT
            ).show()
        }

        pdfDocument.close()

        MediaScannerConnection.scanFile(
            context,
            arrayOf(path),
            null
        ) { _, _ -> }
    }
}
