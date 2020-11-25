package com.example.kotlindemo.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/25 9:06
 * @Description: 启动activity工具类
 */

fun gotoActivity(activity: Activity, clazz: Class<Any>) {
    val intent = Intent(activity, clazz)
    activity.startActivity(intent)
}

fun gotoActivity(activity: Activity, clazz: Class<Any>, bundle: Bundle) {
    val intent = Intent(activity, clazz)
    intent.putExtras(bundle)
    activity.startActivity(intent)
}