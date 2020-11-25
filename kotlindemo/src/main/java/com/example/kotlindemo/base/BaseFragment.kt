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

    private lateinit var rootView: View
    protected lateinit var mContext: Context

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

        rootView = inflater.inflate(getLayoutId(), container, false)
//        initView(rootView, savedInstanceState)
//        initData()
        return rootView
    }

    /**
     * 有一点特别注意在onCreateView中不直接访问视图因为视图没有加载完成容易引起空指针,需要在onViewCreated中访问视图
     * 所以initView()放在onViewCreated()方法中，而不是onCreateView中
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(rootView, savedInstanceState)
        initData()
    }

    abstract fun initView(rootView: View, savedInstanceState: Bundle?)

    open fun initData() {
    }

    abstract fun getLayoutId(): Int
}