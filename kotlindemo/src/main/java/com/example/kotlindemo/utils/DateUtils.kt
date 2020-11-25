package com.example.kotlindemo.utils

import java.text.SimpleDateFormat
import java.util.*

fun format(time: Long): String {
    val date = Date(time)
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    return sdf.format(date)
}