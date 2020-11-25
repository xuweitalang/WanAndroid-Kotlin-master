package com.example.kotlindemo.base.mvp

import com.example.kotlindemo.base.BaseResponse
import com.example.kotlindemo.http.BaseObserver
import com.example.kotlindemo.http.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/24 16:46
 * @Description:
 */
open class BasePresenter<V : IView> : IPresenter<V> {
    private lateinit var viewReference: WeakReference<V>
    private var disposable: CompositeDisposable = CompositeDisposable()

    fun <T> addSubscribe(observable: Observable<BaseResponse<T>>, subscribe: BaseObserver<T>) {
        val subscribe = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(subscribe)
        disposable.add(subscribe)
    }

    fun unSubscribe() {
        disposable.dispose()
    }

    fun <D> create(clazz: Class<D>): D {
        return RetrofitClient.get().retrofit.create(clazz)
    }

    override fun attachView(view: V) {
        viewReference = WeakReference(view)
    }

    override fun detachView() {
        viewReference.clear()
    }

    override fun getView(): V? {
        return viewReference.get()
    }

    override fun isViewAttached(): Boolean {
        return viewReference.get() != null
    }
}