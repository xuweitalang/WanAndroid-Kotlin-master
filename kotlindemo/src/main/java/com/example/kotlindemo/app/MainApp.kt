package com.example.kotlindemo.app

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import com.example.kotlindemo.R
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/24 15:36
 * @Description:
 */
class MainApp : Application() {

    private lateinit var cookieJar: PersistentCookieJar

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
            override fun createRefreshHeader(
                context: Context,
                layout: RefreshLayout
            ): RefreshHeader {
                //指定为经典Header，默认是 贝塞尔雷达 Header
                return ClassicsHeader(Companion.context)
//                    .setPrimaryColor(ContextCompat.getColor(Companion.context, R.color.colorAccent))  // header 背景
                    .setAccentColor(
                        ContextCompat.getColor(
                            Companion.context,
                            R.color.black_f222
                        )
                    ) // header 中文字，icon 颜色
            }
        })

        SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator {
            override fun createRefreshFooter(
                context: Context,
                layout: RefreshLayout
            ): RefreshFooter {
                //                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)  //全局设置主题颜色
                return ClassicsFooter(Companion.context)   //指定为经典Header，默认是 贝塞尔雷达Header
//                    .setPrimaryColor(ContextCompat.getColor(Companion.context, R.color.colorAccent))  // footer 背景
                    .setAccentColor(
                        ContextCompat.getColor(
                            Companion.context,
                            R.color.black_f222
                        )
                    ) // footer 中文字，icon 颜色
            }

        })
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        instance = this
        cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))
    }

    fun getPersistentCookieJar(): PersistentCookieJar {
        return cookieJar
    }


    companion object {
        private lateinit var context: Context
        private lateinit var instance: MainApp

        fun getContext(): Context {
            return context.applicationContext
        }

        fun getInstance(): MainApp {
            return instance
        }
    }
}