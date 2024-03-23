package com.example.weatherforecast.model

data class CurrentModel(
    val relative_humidity_2m: Int?,
    val is_day: Int?,
    val precipitation: Double?,
    val temperature_2m: Double?
)
