package com.example.kotlindemo.home.contract

import com.example.kotlindemo.base.mvp.IView
import com.example.kotlindemo.home.bean.Article
import com.example.kotlindemo.home.bean.Banner

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/25 14:31
 * @Description:
 */
interface HomeContract {
    interface View : IView {
        fun onBanner(list: List<Banner>?)

        fun onArticles(page: Int, list: List<Article>?)
    }

    interface Presenter {
        fun getBanner()

        fun getArticles(page: Int)
    }
}