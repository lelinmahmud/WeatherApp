package com.lelin.weatherapp.screens.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelin.weatherapp.data.DataOrException
import com.lelin.weatherapp.model.Weather
import com.lelin.weatherapp.model.WeatherObject
import com.lelin.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) :ViewModel() {


    suspend fun getWeather(cityName: String): DataOrException<Weather,Boolean,Exception>{
       return repository.getWeather(cityName)
    }


}