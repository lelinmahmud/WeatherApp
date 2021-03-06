package com.lelin.weatherapp.repository

import com.lelin.weatherapp.data.DataOrException
import com.lelin.weatherapp.model.Weather
import com.lelin.weatherapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi) {

    suspend fun getWeather(): DataOrException<Weather,Boolean,Exception>{

        val response = try {
           weatherApi.getWeather()
        }catch (exception:Exception){
            return DataOrException(exception = exception)
        }

        return DataOrException(data = response)
    }

}