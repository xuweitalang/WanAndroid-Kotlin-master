package com.example.kotlindemo.main.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kotlindemo.common.bean.FragmentItem

/**
 *
 * @Author:        xuwei
 * @Date:          2020/11/25 14:41
 * @Description:
 */
class MainViewPageAdapter(
    var context: Context,
    fragmentManager: FragmentManager,
    var fragmentItems: List<FragmentItem>
) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return fragmentItems[position].fragment
    }

    override fun getCount(): Int {
        return fragmentItems.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentItems[position].title
    }
}