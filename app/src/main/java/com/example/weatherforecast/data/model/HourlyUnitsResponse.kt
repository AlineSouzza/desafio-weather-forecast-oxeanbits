package com.example.weatherforecast.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HourlyUnitsResponse(
    val pressure_msl: String?,
    val temperature_2m: String?,
    val relativehumidity_2m: String?,
    val weathercode: String?,
    val windspeed_10m: String?,
    val time: String?,
) : Parcelable
