package com.example.weatherforecast.presenter.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.weatherforecast.network.RemoteDataSource
import com.example.weatherforecast.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SearchWeatherForecastViewModel: ViewModel() {

//    private val _uiState: MutableStateFlow<UIState> = MutableStateFlow(UIState())
//
//    fun getWeatherForecast() = liveData(Dispatchers.IO) {
//        try {
//            emit(StateView.Loading())
//
//            val response = getWeatherForecastUseCase.getWeatherForecast()
//
//            emit(StateView.Success(response))
//        } catch (e: HttpException) {
//            e.printStackTrace()
//            emit(StateView.Error(message = e.message))
//        } catch (e: Exception) {
//            e.printStackTrace()
//            emit(StateView.Error(message = e.message))
//        }
//    }
}