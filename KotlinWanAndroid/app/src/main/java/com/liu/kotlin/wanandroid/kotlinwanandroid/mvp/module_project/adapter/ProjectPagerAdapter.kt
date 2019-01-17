package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.ProjectType
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.fragment.ProjectFragment

/**
 * author: liu
 * date: 2019/1/17 16:25
 * 项目ViewPager adapter
 */
class ProjectPagerAdapter(fm: FragmentManager,private var projectTypeList: List<ProjectType>): FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = ProjectFragment.newInstance(position,projectTypeList[position].id)

    override fun getCount(): Int = if (projectTypeList.isEmpty()) 0 else projectTypeList.size

    override fun getPageTitle(position: Int): CharSequence? = projectTypeList[position]?.name ?: ""

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}