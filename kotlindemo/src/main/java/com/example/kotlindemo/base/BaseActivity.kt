package com.example.kotlindemo.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.common.annotation.EventBusSubscribe
import com.jaeger.library.StatusBarUtil
import org.greenrobot.eventbus.EventBus

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/24 16:06
 * @Description:
 */
abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mContext = this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//黑色
        }
        StatusBarUtil.setColor(this, resources.getColor(R.color.colorPrimary), 0)
        initView()
        initData()
        if (isEventBusAnnotationPresent()) {
            EventBus.getDefault().register(this)
        }
    }

    private fun isEventBusAnnotationPresent(): Boolean {
        return javaClass.isAnnotationPresent(EventBusSubscribe::class.java)
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()
    open fun initData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isEventBusAnnotationPresent()) {
            EventBus.getDefault().unregister(this)
        }
    }
}