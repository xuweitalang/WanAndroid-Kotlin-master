package com.example.kotlindemo.api

import com.example.kotlindemo.base.BaseResponse
import com.example.kotlindemo.home.bean.Article
import com.example.kotlindemo.home.bean.ArticleResponse
import com.example.kotlindemo.home.bean.Banner
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/25 15:36
 * @Description:
 */
interface Api {

    @GET("banner/json")
    fun getBanner(): Observable<BaseResponse<List<Banner>>>

    @GET("article/top/json")
    fun getTopArticle(): Observable<BaseResponse<List<Article>>>

    @GET("article/list/{page}/json")
    fun getArticles(@Path("page") page: Int): Observable<BaseResponse<ArticleResponse>>
}