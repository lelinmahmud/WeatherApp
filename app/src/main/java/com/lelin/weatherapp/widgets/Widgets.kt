package com.lelin.weatherapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.lelin.weatherapp.R
import com.lelin.weatherapp.model.Current
import com.lelin.weatherapp.model.Daily
import com.lelin.weatherapp.utils.formatTime
import com.lelin.weatherapp.utils.formatToDecimal
import com.lelin.weatherapp.utils.getFormatDate

@Composable
fun WeatherStateImage(url: String){
    Image(painter = rememberImagePainter(url), contentDescription = "image", modifier = Modifier.size(80.dp) )
}

@Composable
fun HumidityWindPressureRow(current: Current){
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.humidity), contentDescription = "humidity",
                modifier = Modifier.size(20.dp))
            Text(text = "${current.humidity}", style = MaterialTheme.typography.caption)
        }

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.pressure), contentDescription = "humidity",
                modifier = Modifier.size(20.dp))
            Text(text = "${current.pressure} psi", style = MaterialTheme.typography.caption)
        }

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.wind), contentDescription = "humidity",
                modifier = Modifier.size(20.dp))
            Text(text = "${current.humidity} mph", style = MaterialTheme.typography.caption)
        }


    }
}

@Composable
fun SunSetRow(current: Current){
    Row(
        modifier = Modifier
            .padding(top = 15.dp, bottom = 6.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.sunrise), contentDescription = "sunrise",
                modifier = Modifier.size(30.dp))
            Text(text = formatTime(current.sunrise), style = MaterialTheme.typography.caption)
        }

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.sunset), contentDescription = "sunset",
                modifier = Modifier.size(30.dp))
            Text(text = formatTime(current.sunset), style = MaterialTheme.typography.caption)
        }



    }
}
@Composable
fun WeatherCard(daily: Daily){

    val imageUrl = "https://openweathermap.org/img/wn/${daily.weather.firstOrNull()?.icon}.png"


    Card(
        modifier = Modifier
            .padding(bottom = 5.dp)
            .fillMaxWidth()
            .height(60.dp),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(bottomStartPercent = 50, bottomEndPercent = 50, topStartPercent = 50)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(5.dp)
        ) {

            Text(text = getFormatDate(daily.dt), style = MaterialTheme.typography.subtitle1, modifier = Modifier.padding(horizontal = 15.dp))
            WeatherStateImage(imageUrl)
            Text(text = daily.weather.firstOrNull()!!.main, style = MaterialTheme.typography.subtitle2)
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)){
                    append(formatToDecimal(daily.temp.max) +"℉  ")
                    withStyle(style = SpanStyle(color = Color.Gray, fontWeight = FontWeight.Bold)){
                        append(formatToDecimal(daily.temp.min) +"℉")
                    }
                }
            }, modifier = Modifier.padding(end = 15.dp))



        }

    }
}