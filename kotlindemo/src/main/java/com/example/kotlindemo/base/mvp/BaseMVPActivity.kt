package com.example.kotlindemo.base.mvp

import com.example.kotlindemo.base.BaseActivity

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/24 16:26
 * @Description:
 */
abstract class BaseMVPActivity<in V : IView, P : IPresenter<in V>> : BaseActivity(), IView {

    private lateinit var presenter: P

    override fun initData() {
        super.initData()
        presenter = createPresenter()
        presenter.attachView(this as V)
    }

    abstract fun createPresenter(): P

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}