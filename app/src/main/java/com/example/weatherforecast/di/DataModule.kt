package com.example.weatherforecast.di


import com.example.weatherforecast.data.api.ServiceApi
import com.example.weatherforecast.network.ServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class DataModule {

    @Provides
    @Singleton
    fun providesServiceApi(
        serviceProvider: ServiceProvider
    ): ServiceApi {
        return ServiceProvider.createService(ServiceApi::class.java)
    }
}