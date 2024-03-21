package com.example.weatherforecast.data.mapper

import com.example.weatherforecast.data.model.WeatherForecastResponse
import com.example.weatherforecast.domain.model.WeatherForecast

fun WeatherForecastResponse.toDomain(): WeatherForecast {
    return WeatherForecast(
        latitude = latitude,
        longitude = longitude,
        generationtime_ms = generationtime_ms,
        utc_offset_seconds = utc_offset_seconds,
        timezone = timezone,
        timezone_abbreviation = timezone_abbreviation,
        elevation = elevation
    )
}