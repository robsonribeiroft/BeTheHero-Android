package br.com.rrdev.bethehero.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.RoundingMode
import java.text.DecimalFormat

@Parcelize
data class Incident(
    val id: Int,
    val name: String,
    val email: String,
    val whatsapp: String,
    val title: String,
    val description: String,
    val value: Double): Parcelable{

    fun getValueFormat(): String {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        return "R$ ${df.format(value)} reais"
    }
}