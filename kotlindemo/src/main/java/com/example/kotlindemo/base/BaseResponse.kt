package com.example.kotlindemo.base

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/24 17:06
 * @Description:
 */
data class BaseResponse<T>(
    var data: T?,
    var results: T?,
    val errorMsg: String? = null,
    var errorCode: Int? = -1,
    var error: Boolean? = true
)