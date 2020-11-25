package com.example.kotlindemo.main.presenter

import com.example.kotlindemo.base.mvp.BasePresenter
import com.example.kotlindemo.main.contract.MainContract

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/25 10:55
 * @Description:
 */
class MainPresenter : BasePresenter<MainContract.View>(),MainContract.Presenter {
    override fun getUserInfo() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}