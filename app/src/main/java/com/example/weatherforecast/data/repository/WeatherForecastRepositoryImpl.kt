package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.api.ServiceApi
import com.example.weatherforecast.data.model.WeatherForecastResponse
import com.example.weatherforecast.domain.repository.WeatherForecastRepository
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val service: ServiceApi
): WeatherForecastRepository {
    override suspend fun getWeatherForecast(weatherForecast: String): WeatherForecastResponse {
        return service.getWeatherForecast(weatherForecast)
    }
}