package com.example.weatherforecast.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class WeatherForecastModel(
    val latitude: Double?,
    val longitude: Double?,
    val timezone: String?,
    val timezone_abbreviation: String?,
    val elevation: Double?,
    val hourly_units: HourlyUnitsModel?,
    val hourly: HourlyModel?,
    val daily: DailyModel?,
    val current: CurrentModel?,
)


