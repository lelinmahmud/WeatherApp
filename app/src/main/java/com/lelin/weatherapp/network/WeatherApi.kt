package com.lelin.weatherapp.network

import com.lelin.weatherapp.model.Weather
import com.lelin.weatherapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/onecall")
    suspend fun getWeather(
        @Query("exclude") query: String ="hourly,minutely",
        @Query("lat") lat: String ="23.44",
        @Query("lon") lon: String = "90.04",
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = Constants.API_KEY,

        ): Weather
}