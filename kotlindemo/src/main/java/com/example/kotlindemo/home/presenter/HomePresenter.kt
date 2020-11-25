package com.example.kotlindemo.home.presenter

import com.example.kotlindemo.api.Api
import com.example.kotlindemo.base.BaseResponse
import com.example.kotlindemo.base.mvp.BasePresenter
import com.example.kotlindemo.home.bean.Article
import com.example.kotlindemo.home.bean.ArticleResponse
import com.example.kotlindemo.home.bean.Banner
import com.example.kotlindemo.home.contract.HomeContract
import com.example.kotlindemo.http.BaseObserver
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/25 14:33
 * @Description:
 */
class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {
    private val dataList = ArrayList<Article>()

    override fun getBanner() {
        addSubscribe(create(Api::class.java).getBanner(), object : BaseObserver<List<Banner>>() {
            override fun onSuccess(results: List<Banner>?) {
                getView()?.onBanner(results)
            }

        })
    }

    override fun getArticles(page: Int) {
        var api = create(Api::class.java)

        var observable = Observable.zip(api.getTopArticle(), api.getArticles(page),
            object :
                BiFunction<BaseResponse<List<Article>>, BaseResponse<ArticleResponse>, BaseResponse<List<Article>>> {
                override fun apply(
                    resp1: BaseResponse<List<Article>>,
                    resp2: BaseResponse<ArticleResponse>
                ): BaseResponse<List<Article>> {
                    if (page == 0) {
                        dataList.clear()
                        resp1.data?.let { dataList.addAll(it) }
                    }
                    val data = resp2.data
                    val articles = data?.datas
                    articles?.let { dataList.addAll(it) }
                    return BaseResponse(dataList, dataList, resp1.errorMsg, resp1.errorCode, false)
                }
            })
        addSubscribe(observable, object : BaseObserver<List<Article>>() {
            override fun onSuccess(results: List<Article>?) {
                getView()?.onArticles(page, results)
            }

        })
    }
}