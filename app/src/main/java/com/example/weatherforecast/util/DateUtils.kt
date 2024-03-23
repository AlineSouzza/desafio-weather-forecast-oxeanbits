package com.example.weatherforecast.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun stringToDate(stringDate: String): LocalDateTime {
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        return LocalDateTime.parse(stringDate, dateFormat);
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatLocalDateToTime(dateTime: LocalDateTime): String? {
        try {
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            return  dateTime.format(formatter)   // format output
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatLocalDateToDate(dateTime: LocalDateTime): String? {
        try {
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            return  dateTime.format(formatter)   // format output
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}