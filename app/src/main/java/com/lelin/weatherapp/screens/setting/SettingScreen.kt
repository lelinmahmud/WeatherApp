package com.lelin.weatherapp.screens.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lelin.weatherapp.widgets.WeatherAppBar

@Composable
fun SettingScreen(navController: NavController){

    Scaffold(topBar = {
        WeatherAppBar(
            title = "Setting",
            navController = navController,
            icon = Icons.Default.ArrowBack,
            elevation = 5.dp,
            isMainScreen = false
        ) {
            navController.popBackStack()
        }
    }) {
        Surface(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Setting Screen", style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
            }
        }
    }


}