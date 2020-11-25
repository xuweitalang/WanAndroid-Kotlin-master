package com.example.kotlindemo.home.bean

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/25 15:19
 * @Description:
 */
data class Article(
    val id: Int,
    val title: String,
    val author: String,
    val publishTime: Long,
    val superChapterName: String,
    val envelopePic: String,
    val link: String,
    val desc: String,
    val fresh: Boolean,
    val collect: Boolean,
    val type: Int
)