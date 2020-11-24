package com.example.kotlindemo.base.mvp

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/24 16:34
 * @Description:
 */
interface IPresenter<V : IView> {
    fun attachView(view: V)
    fun detachView()
    fun getView(): V?
    fun isViewAttached(): Boolean
}