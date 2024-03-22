package com.example.weatherforecast.data.api

import com.example.weatherforecast.model.WeatherForecastModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("forecast?hourly=temperature_2m&daily=uv_index_max&current=relative_humidity_2m&daily=precipitation_probability_max&current=is_day")
    fun getWeatherForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Call<WeatherForecastModel>
}