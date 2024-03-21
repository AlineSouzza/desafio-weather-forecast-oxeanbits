package com.example.weatherforecast.network

import com.example.weatherforecast.data.api.ServiceApi

class RemoteDataSource(private val serviceApi: ServiceApi) {
    fun getWeartherForecast() {
        Thread {
            val response = serviceApi.getWeatherForecast("-12.900975")

        }.start()
    }
}