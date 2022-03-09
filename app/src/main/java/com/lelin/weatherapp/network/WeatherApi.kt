package com.lelin.weatherapp.network

import com.lelin.weatherapp.model.Weather
import com.lelin.weatherapp.model.WeatherObject
import com.lelin.weatherapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/weather")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = Constants.API_KEY,

        ): Weather
}