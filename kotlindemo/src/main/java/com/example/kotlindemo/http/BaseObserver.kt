package com.example.kotlindemo.http

import android.util.Log
import com.example.kotlindemo.base.BaseResponse
import com.example.kotlindemo.base.mvp.IView
import io.reactivex.observers.DisposableObserver

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/24 17:10
 * @Description:
 */
abstract class BaseObserver<T> : DisposableObserver<BaseResponse<T>> {
    private var baseView: IView? = null

    constructor() : super()
    constructor(view: IView) : super() {
        this.baseView = view
    }

    override fun onStart() {
        super.onStart()
        baseView?.showLoading()
    }

    override fun onNext(response: BaseResponse<T>) {
        baseView?.hideLoading()
        Log.d("debug", "response = $response")
        val errorCode: Int = response.errorCode ?: -1
        val errorMsg: String = response.errorMsg ?: ""
        val error: Boolean = response.error ?: true
        if ((errorCode == 0) or (errorCode == 200)) {
            onSuccess(response.data)
        } else if (!error) {
            onSuccess(response.results)
        } else {
            onError(ApiException(errorCode, errorMsg))
        }
    }

    abstract fun onSuccess(results: T?)

    override fun onComplete() {
        baseView?.hideLoading()
    }

    override fun onError(e: Throwable) {
        ExceptionHandler.handleException(e)
    }
}