package com.lelin.weatherapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lelin.weatherapp.navigation.WeatherScreens

@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClick: () -> Unit = {},
    onButtonClick: () -> Unit = {}
) {

    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = MaterialTheme.colors.onSecondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = { onAddActionClick.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                }
                IconButton(onClick = {
                    showDialog.value = true
                }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = "More Icon"
                    )
                }
            } else {
                Box {

                }
            }
        },
        navigationIcon = {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "back icon",
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier
                        .clickable {
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

@Composable
fun ShowSettingDropDownMenu(
    showDialog: MutableState<Boolean>,
    navController: NavController
) {
    var expanded = remember {
        mutableStateOf(true)
    }
    val items = listOf("About", "Favorites", "Setting")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {

        DropdownMenu(
            expanded = expanded.value, onDismissRequest = {
                showDialog.value = false
                expanded.value = false },
            modifier = Modifier.width(140.dp)
        ) {

            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    expanded.value = false
                    showDialog.value = false
                }) {
                    Icon(
                        imageVector = when (s) {

                            "About" -> Icons.Default.Info
                            "Favorites" -> Icons.Default.FavoriteBorder
                            else -> Icons.Default.Settings
                        }, contentDescription = null, tint = Color.LightGray
                    )
                    Text(text = s, modifier = Modifier.clickable {
                        navController.navigate(
                            when (s){
                                "About" -> WeatherScreens.AboutScreen.name
                                "Favorites" ->  WeatherScreens.FavoriteScreen.name
                                else ->  WeatherScreens.SettingScreen.name
                            }
                        )
                    }, fontWeight = FontWeight.W300)

                }
            }

        }

    }

}
