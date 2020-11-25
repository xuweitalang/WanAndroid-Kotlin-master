package com.example.kotlindemo.main.contract

import com.example.kotlindemo.base.mvp.IView
import com.example.kotlindemo.db.bean.User

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/25 10:45
 * @Description:
 */
interface MainContract {
    interface View : IView {
        fun setUserInfo(user: User)
    }

    interface Presenter {
        fun getUserInfo()
    }
}