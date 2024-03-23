package com.example.weatherforecast.network

import com.example.weatherforecast.data.api.ServiceApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val baseUrl = "https://api.open-meteo.com/v1/"

fun client(): OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    .build()

fun gson(): Gson = GsonBuilder().create()

fun retrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(client())
    .addConverterFactory(GsonConverterFactory.create(gson()))
    .build()

fun services(): ServiceApi =
    retrofit().create(ServiceApi::class.java)