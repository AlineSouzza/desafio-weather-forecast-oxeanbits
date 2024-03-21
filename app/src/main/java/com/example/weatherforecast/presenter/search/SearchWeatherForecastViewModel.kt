package com.example.weatherforecast.presenter.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.weatherforecast.domain.usecase.GetWeatherForecastUseCase
import com.example.weatherforecast.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SearchWeatherForecastViewModel @Inject constructor(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase
) : ViewModel() {

    fun getWeatherForecast(weatherForecast: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val response = getWeatherForecastUseCase(weatherForecast)

            emit(StateView.Success(response))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }
}