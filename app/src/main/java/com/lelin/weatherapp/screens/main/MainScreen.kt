package com.lelin.weatherapp.screens.main

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lelin.weatherapp.data.DataOrException
import com.lelin.weatherapp.model.Weather

@Composable
fun MainScreen(navController: NavController,viewModel: MainViewModel){
    ShowData(viewModel = viewModel)
}

@Composable
fun ShowData(viewModel: MainViewModel){

    val weatherData = produceState<DataOrException<Weather,Boolean,Exception>>(
        initialValue = DataOrException(loading = true)){
        value = viewModel.getWeather("London,uk")
    }.value

    if (weatherData.loading == true){
        CircularProgressIndicator()
    }
    else if (weatherData.data != null){
        Text(text = "Main Screen ${weatherData!!.data?.city?.name}")

    }


}
