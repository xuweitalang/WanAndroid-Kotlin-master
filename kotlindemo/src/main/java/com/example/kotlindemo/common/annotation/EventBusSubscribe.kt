package com.example.kotlindemo.common.annotation

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/24 16:15
 * @Description: 通过此注解来判断是否需要注册EventBus
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class EventBusSubscribe {
}