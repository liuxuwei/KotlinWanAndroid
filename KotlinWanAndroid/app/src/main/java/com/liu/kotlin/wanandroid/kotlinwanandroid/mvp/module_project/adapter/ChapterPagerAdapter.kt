package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.ViewGroup
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Chapters
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.fragment.ArticleFragment
import com.orhanobut.logger.Logger

/**
 * author: liu
 * date: 2019/1/15 18:33
 * description
 */
class ChapterPagerAdapter(fm: FragmentManager, private var chapters: List<Chapters>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return ArticleFragment.newInstance(position, chapters[position].id)
    }

    override fun getCount(): Int = if (chapters.isEmpty()) 0 else chapters.size

    override fun getPageTitle(position: Int): CharSequence? = chapters[position]?.name ?: ""

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}