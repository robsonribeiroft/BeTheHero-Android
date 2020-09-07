package br.com.rrdev.bethehero.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.RoundingMode
import java.text.DecimalFormat

@Parcelize
data class Incident(
    val city: String,
    val description: String,
    val email: String,
    val id: Int,
    val img_url: String,
    val name: String,
    val ong_id: String,
    val sensible_content: Int,
    val title: String,
    val uf: String,
    val value: Int,
    val whatsapp: String): Parcelable{

    fun getValueFormat(): String {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        return "R$ ${df.format(value)}"
    }
}