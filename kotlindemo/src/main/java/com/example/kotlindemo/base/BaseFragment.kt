package com.example.kotlindemo.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/24 16:22
 * @Description:
 */
abstract class BaseFragment : Fragment() {

    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        mContext = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getLayoutId(), container, false)
        initView(rootView, savedInstanceState)
        initData()
        return rootView
    }

    abstract fun initView(rootView: View, savedInstanceState: Bundle?)

    open fun initData() {
    }

    abstract fun getLayoutId(): Int
}