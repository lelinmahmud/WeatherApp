package com.lelin.weatherapp.screens.main

import androidx.lifecycle.ViewModel
import com.lelin.weatherapp.data.DataOrException
import com.lelin.weatherapp.model.Weather
import com.lelin.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) :ViewModel() {


    suspend fun getWeather(): DataOrException<Weather,Boolean,Exception>{
       return repository.getWeather()
    }


}