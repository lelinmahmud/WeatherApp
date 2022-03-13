package com.lelin.weatherapp.screens.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.lelin.weatherapp.R
import com.lelin.weatherapp.data.DataOrException
import com.lelin.weatherapp.model.Current
import com.lelin.weatherapp.model.Daily
import com.lelin.weatherapp.model.Weather
import com.lelin.weatherapp.navigation.WeatherScreens
import com.lelin.weatherapp.utils.formatDate
import com.lelin.weatherapp.utils.formatTime
import com.lelin.weatherapp.utils.formatToDecimal
import com.lelin.weatherapp.utils.getFormatDate
import com.lelin.weatherapp.widgets.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "MainScreen"

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel, city: String?){

    Log.e(TAG, "MainScreen: city is :$city")

    val weatherData = produceState<DataOrException<Weather,Boolean,Exception>>(
        initialValue = DataOrException(loading = true)){
        value = viewModel.getWeather()
    }.value

    if (weatherData.loading == true){
        CircularProgressIndicator()
    }
    else if (weatherData.data != null){
        MainScaffold(weather = weatherData.data!!, navController = navController)
    }
    else{
        Text(text = "Main Screen ${weatherData!!.exception?.localizedMessage}")

    }

}

@Composable
fun MainScaffold(weather: Weather,navController: NavController){

    Scaffold(topBar = {
        WeatherAppBar(
            title = weather.timezone,
            navController = navController,
            onAddActionClick = {
                  navController.navigate(WeatherScreens.SearchScreen.name)
            },
            elevation = 5.dp ){
            Log.e("TAG", "MainScaffold: onButton clicked" )
        }
    }) {
        MainContent(weather)
    }



}

@Composable
fun MainContent(weather: Weather) {

    val imageUrl = "https://openweathermap.org/img/wn/${weather.current.weather.firstOrNull()?.icon}.png"
    
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = formatDate(weather.current.dt),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
            )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                WeatherStateImage(imageUrl)
                Text(
                    text = formatToDecimal(weather.current.temp)+"℉",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = "${weather.current.weather.firstOrNull()?.main}",
                    fontStyle = FontStyle.Italic
                )

            }

        }

        HumidityWindPressureRow(current = weather.current)
        Divider()
        SunSetRow(current = weather.current)
        Text(text = "This Week", style = MaterialTheme.typography.h5)
        WeatherWeekView(daily = weather.daily.toMutableList())

    }


    
}


@Composable
fun WeatherWeekView(daily: MutableList<Daily>){
    
    Surface(
        modifier = Modifier
            .padding(top = 2.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
        color = Color(0xFFEEF1EF),
        shape = RoundedCornerShape(size =14.dp)
    ) {
        LazyColumn(contentPadding = PaddingValues(1.dp), modifier = Modifier.padding(2.dp)){
            itemsIndexed(items = daily){ index, item ->
                WeatherCard(item)
            }
        }
    }
    
}


