package com.example.weatherforecast.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.example.weatherforecast.data.model.HourlyResponse
import com.example.weatherforecast.data.model.HourlyUnitsResponse

@Parcelize
data class WeatherForecast(
    val latitude: Double?,
    val longitude: Double?,
    val generationtime_ms: Double?,
    val utc_offset_seconds: Int?,
    val timezone: String?,
    val timezone_abbreviation: String?,
    val elevation: Int?,
): Parcelable{

    fun getFullAddress(): String {
        return "$latitude - $longitude"
    }
}


