package com.example.kotlindemo.utils

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

/**
 * 顶层声明
 */
fun logInterceptor(): Interceptor {
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}