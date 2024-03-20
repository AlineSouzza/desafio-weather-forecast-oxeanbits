package com.example.weatherforecast.di

import com.example.weatherforecast.network.ServiceProvider
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)

class NetworkModule {
    fun providersServiceProvider() = ServiceProvider
}