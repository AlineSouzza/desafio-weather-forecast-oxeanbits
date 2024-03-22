package com.example.weatherforecast.model

data class HourlyModel(
    val temperature_2m: List<Double>,
    val relative_humidity_2m: List<Double>?,
    val time: List<String>?,
)
