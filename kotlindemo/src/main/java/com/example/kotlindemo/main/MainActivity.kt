package com.example.kotlindemo.main

import android.app.Activity
import android.graphics.BitmapFactory
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlindemo.R
import com.example.kotlindemo.base.mvp.BaseMVPActivity
import com.example.kotlindemo.common.bean.FragmentItem
import com.example.kotlindemo.db.bean.User
import com.example.kotlindemo.home.HomeFragment
import com.example.kotlindemo.main.adapter.MainViewPageAdapter
import com.example.kotlindemo.main.contract.MainContract
import com.example.kotlindemo.main.presenter.MainPresenter
import com.example.kotlindemo.meizi.MeiziActivity
import com.example.kotlindemo.utils.blur
import com.example.kotlindemo.utils.gotoActivity
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMVPActivity<MainContract.View, MainPresenter>(), MainContract.View,
    View.OnClickListener {

    private lateinit var mainPageAdapter: MainViewPageAdapter
    private lateinit var ivUser: ImageView
    private lateinit var tvUserName: TextView

    override fun getLayoutId() = R.layout.activity_main

    override fun initView() {
        StatusBarUtil.setColorForDrawerLayout(
            this,
            dl_drawer_layout,
            resources.getColor(R.color.colorPrimary),
            0
        )

        iv_main_menu.setOnClickListener {
            openDrawer()
        }
        val headView = nv_left_navigation.getHeaderView(0)
        tvUserName = headView.findViewById(R.id.tv_nav_username)
        ivUser = headView.findViewById(R.id.iv_avatar_background)
        tvUserName.setOnClickListener(this)

        nv_left_navigation.setNavigationItemSelectedListener { menuItem ->
            closeDrawer()
            when (menuItem.itemId) {
                R.id.item_nav_happy_minute -> {
                    gotoActivity(mContext as Activity, MeiziActivity().javaClass)
                }
                R.id.item_nav_about -> {
                    gotoActivity(mContext as Activity, MeiziActivity().javaClass)
                }
                R.id.item_nav_favorite -> {
                    gotoActivity(mContext as Activity, MeiziActivity().javaClass)
                }
                R.id.item_nav_setting -> {
                    gotoActivity(mContext as Activity, MeiziActivity().javaClass)
                }
            }
            true
        }
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.avatar)
        ivUser.setImageBitmap(blur(this, bitmap, 22))
    }

    override fun initData() {
        super.initData()
        val list = mutableListOf<FragmentItem>()
        list.add(FragmentItem("首页", HomeFragment.newInstance()))
        list.add(FragmentItem("项目", HomeFragment.newInstance()))
        list.add(FragmentItem("体系", HomeFragment.newInstance()))
        list.add(FragmentItem("干货", HomeFragment.newInstance()))
        mainPageAdapter = MainViewPageAdapter(this, supportFragmentManager, list)
        vp_main_pager.adapter = mainPageAdapter
        tl_main_tab.setupWithViewPager(vp_main_pager)
        vp_main_pager.currentItem = 0
    }

    private fun openDrawer() {
        dl_drawer_layout.openDrawer(Gravity.START)
    }

    private fun closeDrawer() {
        dl_drawer_layout.closeDrawer(Gravity.START)
    }

    override fun createPresenter(): MainPresenter = MainPresenter()

    override fun setUserInfo(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_nav_username -> {

            }
        }
    }
}
