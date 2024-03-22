package com.example.weatherforecast.model

data class DailyModel(
    val uv_index_max: List<Double>?,
    val time: List<String>?,
    val precipitation_probability_max: List<Int>?,
)
