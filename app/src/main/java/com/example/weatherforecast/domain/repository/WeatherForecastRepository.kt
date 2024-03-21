package com.example.weatherforecast.domain.repository

import com.example.weatherforecast.data.model.WeatherForecastResponse

interface WeatherForecastRepository {

    suspend fun getWeatherForecast(weatherForecast: String): WeatherForecastResponse
}