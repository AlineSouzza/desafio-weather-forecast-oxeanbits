package com.example.weatherforecast.model

data class DailyModel(
    val uv_index_max: List<Double>?,
    val time: List<String>?,
    val sunrise: List<String>,
    val sunset: List<String>,
)
