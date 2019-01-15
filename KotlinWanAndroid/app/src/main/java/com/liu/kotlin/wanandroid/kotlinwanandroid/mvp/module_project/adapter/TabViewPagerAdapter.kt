package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Chapters

/**
 * author: liu
 * date: 2019/1/15 18:33
 * description
 */
class TabViewPagerAdapter(private val fm: FragmentManager,var fragmentList: List<Fragment>,var chapters: List<Chapters>): FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        return fragmentList[p0]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return chapters[position]?.name ?: ""
    }
}