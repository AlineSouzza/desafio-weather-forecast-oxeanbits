package com.example.weatherforecast.di

import com.example.weatherforecast.data.repository.WeatherForecastRepositoryImpl
import com.example.weatherforecast.domain.repository.WeatherForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)

abstract class DomainModule {
    @Binds
    abstract fun bindWeatherForecastRepositoryImpl(
        weatherForecastRepositoryImpl: WeatherForecastRepositoryImpl
    ): WeatherForecastRepository
}