package com.example.weatherforecast.domain.usecase

import com.example.weatherforecast.data.mapper.toDomain
import com.example.weatherforecast.domain.model.WeatherForecast
import com.example.weatherforecast.domain.repository.WeatherForecastRepository
import javax.inject.Inject

class GetWeatherForecastUseCase @Inject constructor(
    private val repository: WeatherForecastRepository
) {

    suspend operator fun invoke(weatherForecast: String): WeatherForecast {
        return repository.getWeatherForecast(weatherForecast).toDomain()
    }
}