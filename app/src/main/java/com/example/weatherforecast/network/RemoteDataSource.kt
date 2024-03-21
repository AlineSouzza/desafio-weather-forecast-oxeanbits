package com.example.weatherforecast.network

import com.example.weatherforecast.data.api.ServiceApi
import com.example.weatherforecast.model.WeatherForecastModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val serviceApi: ServiceApi) {
    fun getWeatherForecast() {

        serviceApi.getWeatherForecast().enqueue(object: Callback<WeatherForecastModel>{
            override fun onResponse(
                call: Call<WeatherForecastModel>,
                response: Response<WeatherForecastModel>
            ) {
                if (response.isSuccessful){

                }

            }

            override fun onFailure(call: Call<WeatherForecastModel>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }
}