package com.msf.githubissues.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object ConvertUtil {

    private val locale = Locale("pt", "BR")

    @SuppressLint("SimpleDateFormat")
    fun getDateFormatted(value: String): String{
        val date =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(value)
        val formatted = SimpleDateFormat("dd/MM/yy HH:mm", locale)
        return formatted.format(date)
    }
}