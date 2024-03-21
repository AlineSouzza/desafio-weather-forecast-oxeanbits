package com.example.weatherforecast.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HourlyResponse(
    val pressure_msl: List<Double>?,
    val temperature_2m: List<Double>?,
    val relativehumidity_2m: List<Double>?,
    val weathercode: List<Double>?,
    val windspeed_10m: List<Double>?,
    val time: List<Double>?,
) : Parcelable
