package com.lelin.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(timestamp: Int) : String{

    val sdf = SimpleDateFormat("EEE, MMM d")
    val date = Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun getFormatDate(timestamp: Int) : String{

    val sdf = SimpleDateFormat("EEE")
    val date = Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun formatTime(timestamp: Int) : String{

    val sdf = SimpleDateFormat("hh:mm a")
    val date = Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun formatToDecimal(item: Double) : String{
    return " %.0f".format(item)
}