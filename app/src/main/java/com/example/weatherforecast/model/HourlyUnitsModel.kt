package com.example.weatherforecast.model

data class HourlyUnitsModel(
    val pressure_msl: String?,
    val temperature_2m: String?,
    val relative_humidity_2m: String?,
    val weathercode: String?,
    val windspeed_10m: String?,
    val time: String?,
)
