package com.example.weatherforecast.data.api

import com.example.weatherforecast.model.WeatherForecastModel
import retrofit2.Call
import retrofit2.http.GET

interface ServiceApi {
    @GET("forecast?latitude=-12.900975&longitude=-38.423601&hourly=temperature_2m,relative_humidity_2m")
    fun getWeatherForecast(): Call<WeatherForecastModel>
}