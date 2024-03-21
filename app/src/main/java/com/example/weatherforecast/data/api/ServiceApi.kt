package com.example.weatherforecast.data.api

import com.example.weatherforecast.data.model.WeatherForecastResponse
import retrofit2.http.Path
import retrofit2.http.GET

interface ServiceApi {
    @GET("latitude={latitude}&longitude=-38.423601&hourly=temperature_2m,relative_humidity_2m")
    fun getWeatherForecast(
        @Path("latitude") latitude: String
    ): WeatherForecastResponse
}