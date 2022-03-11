package com.lelin.weatherapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WeatherAppBar(
    title: String ="Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClick: ()-> Unit ={},
    onButtonClick: ()-> Unit ={}
){
    TopAppBar(
        title = {
            Text(text = title, style = TextStyle(color = MaterialTheme.colors.onSecondary, fontWeight = FontWeight.Bold, fontSize = 15.sp))
        },
        actions = {
                  if (isMainScreen){
                      IconButton(onClick = {}) {
                          Icon(
                              imageVector = Icons.Default.Search,
                              contentDescription = "Search Icon"
                          )
                      }
                      IconButton(onClick = {}) {
                          Icon(
                              imageVector = Icons.Rounded.MoreVert,
                              contentDescription = "More Icon"
                          )
                      }
                  }
            else{
                Box{

                }
                  }
        },
        navigationIcon = {
                         if (icon != null){
                             Icon(
                                 imageVector = icon,
                                 contentDescription ="back icon",
                                 tint = MaterialTheme.colors.onSecondary,
                                 modifier = Modifier.clickable {
                                     onButtonClick.invoke()
                                 }
                                     .padding(start = 10.dp)
                                 )
                         }
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}