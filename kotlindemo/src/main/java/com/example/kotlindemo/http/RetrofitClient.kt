package com.example.kotlindemo.http

import com.example.kotlindemo.app.MainApp
import com.example.kotlindemo.utils.logInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 单例的RetrofitClient封装
 */
class RetrofitClient() {
    var retrofit: Retrofit
    private var okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
        .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
        .addInterceptor(logInterceptor())
        .cookieJar(MainApp.getInstance().getPersistentCookieJar())
        .build()

    init {
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://www.wanandroid.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private var instance: RetrofitClient? = null
            get() {
                if (field == null) {
                    field = RetrofitClient()
                }
                return field
            }

        @Synchronized
        fun get(): RetrofitClient {
            return instance!!
        }
    }
}