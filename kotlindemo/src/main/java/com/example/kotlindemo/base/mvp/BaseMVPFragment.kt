package com.example.kotlindemo.base.mvp

import com.example.kotlindemo.base.BaseFragment

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/24 16:42
 * @Description:
 */
abstract class BaseMVPFragment<in V : IView, P : IPresenter<in V>> : BaseFragment(), IView {
    private lateinit var presenter: P

    override fun initData() {
        super.initData()
        presenter = createPresenter()
        presenter.attachView(this as V)
    }

    abstract fun createPresenter(): P
}