package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.activity

import android.content.Context
import android.content.Intent
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.liu.kotlin.wanandroid.kotlinwanandroid.R
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Chapters
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BaseMvpActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.adapter.TabViewPagerAdapter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.contract.ContractProjectAndArticle
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.presenter.ProjectAndArticlePresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.bindView

class ProjectAndArticleActivity : BaseMvpActivity<ProjectAndArticlePresenter, ContractProjectAndArticle.ChapterTypeView>(), ContractProjectAndArticle.ChapterTypeView {
    private val drawParent by bindView<DrawerLayout>(R.id.drawer_parent)
    private val toolbar by bindView<Toolbar>(R.id.toolbar)
    private val tvTitle by bindView<TextView>(R.id.tv_title)
    private val ivMenu by bindView<ImageView>(R.id.iv_menu)
    private val tabNavigation by bindView<TabLayout>(R.id.tab_navigation)
    private val contentViewPager by bindView<ViewPager>(R.id.viewPager)

    private lateinit var pageAdapter: FragmentPagerAdapter
    private var fragmentList = mutableListOf<Fragment>()
    private var mChapters = mutableListOf<Chapters>()

    companion object {


        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, ProjectAndArticleActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun getContentLayoutId(): Int {
        return R.layout.activity_project_article
    }

    override fun getPresenter(): ProjectAndArticlePresenter {
        return ProjectAndArticlePresenter(getContext())
    }

    override fun init() {
        setSupportActionBar(toolbar)


        tabNavigation.setupWithViewPager(contentViewPager)
        setListener()
    }

    private fun setListener() {
        ivMenu.setOnClickListener {
            drawParent.openDrawer(Gravity.START)
        }
    }

    override fun initData() {
        showLoading()
        mPresenter.getChapters()
    }

    override fun getChaptersSuccess(chapterList: List<Chapters>) {
        hideLoading()
        mChapters = chapterList as MutableList<Chapters>
        var tempList = mutableListOf<Fragment>()
        for (position in chapterList.indices) {
            tabNavigation.addTab(tabNavigation.newTab())
        }
        fragmentList.addAll(tempList)
        pageAdapter = TabViewPagerAdapter(supportFragmentManager,mChapters)
        contentViewPager.adapter = pageAdapter
    }


}
