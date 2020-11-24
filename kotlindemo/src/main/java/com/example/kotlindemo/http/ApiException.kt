package com.example.kotlindemo.http

import java.lang.Exception

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/24 17:20
 * @Description:
 */
data class ApiException(var errorCode: Int, var errorMsg: String) : Exception()