package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.activity

import android.content.Context
import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.KeyEvent
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.liu.kotlin.wanandroid.kotlinwanandroid.R
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Chapters
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.ProjectType
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BaseMvpActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.adapter.ChapterPagerAdapter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.adapter.ProjectPagerAdapter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.contract.ContractProjectAndArticle
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.presenter.ProjectAndArticlePresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.activity.LoginActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.DeviceUtil
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.InputSoftUtil
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.bindView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ProjectAndArticleActivity : BaseMvpActivity<ProjectAndArticlePresenter, ContractProjectAndArticle.ChapterTypeView>(), ContractProjectAndArticle.ChapterTypeView {
    private val drawParent by bindView<DrawerLayout>(R.id.drawer_parent)
    private val layoutStatusBar by bindView<LinearLayout>(R.id.layout_status_bar)
    private val toolbar by bindView<Toolbar>(R.id.toolbar)
    private val tvTitle by bindView<TextView>(R.id.tv_title)
    private val ivMenu by bindView<ImageView>(R.id.iv_menu)
    private val tabNavigation by bindView<TabLayout>(R.id.tab_navigation)
    private val contentViewPager by bindView<ViewPager>(R.id.viewPager)
    private val navigationContent by bindView<NavigationView>(R.id.na_drawer_content)
    private lateinit var headerLayout: RelativeLayout
    private lateinit var tvUserName: TextView
    private lateinit var tvLoginBtn:TextView

    private lateinit var chapterPagerAdapter: ChapterPagerAdapter
    private lateinit var projectPagerAdapter: ProjectPagerAdapter
    private var mChapters = mutableListOf<Chapters>()
    private var mProjectTypes = mutableListOf<ProjectType>()
    private var loginState = NOT_LOGIN_STATE

    companion object {
        const val LOGIN_STATE = 1
        const val NOT_LOGIN_STATE = 2

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
        EventBus.getDefault().register(this)
        setSupportActionBar(toolbar)
        DeviceUtil.setStatusBar(layoutStatusBar)
        layoutStatusBar.setBackgroundResource(R.color.color_blue)
        tabNavigation.setupWithViewPager(contentViewPager)
        headerLayout = navigationContent.inflateHeaderView(R.layout.nav_header) as RelativeLayout
        tvUserName = headerLayout.findViewById(R.id.tv_username)
        tvLoginBtn = headerLayout.findViewById(R.id.tv_login)
        setViewPadding()
        setListener()
    }

    private fun setViewPadding() {
        var layoutParams = tvUserName.layoutParams as RelativeLayout.LayoutParams
        layoutParams.topMargin = DeviceUtil.getStatusBarHeight()
        tvUserName.layoutParams = layoutParams
    }

    override fun initData() {
        getChapters()
    }

    private fun getChapters() {
        showLoading()
        mPresenter.getChapters()
    }

    override fun getChaptersSuccess(chapterList: List<Chapters>) {
        hideLoading()
        mChapters = chapterList as MutableList<Chapters>
        for (position in chapterList.indices) {
            tabNavigation.addTab(tabNavigation.newTab())
        }
        chapterPagerAdapter = ChapterPagerAdapter(supportFragmentManager,mChapters)
        contentViewPager.adapter = chapterPagerAdapter
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun getUserName(name: String) {
        tvUserName.text = "Hi:$name"
        tvLoginBtn.text = "退出登录"
        loginState = LOGIN_STATE
    }

    private fun setListener() {

        contentViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                InputSoftUtil.hideInputKeyBoard(this@ProjectAndArticleActivity)
            }

            override fun onPageSelected(p0: Int) {

            }

        })

        ivMenu.setOnClickListener {
            drawParent.openDrawer(Gravity.START)
        }

        tvLoginBtn.setOnClickListener {
            if (loginState == NOT_LOGIN_STATE) {
                startActivity<LoginActivity>()
            } else {
                // todo 退出登录
            }
        }

        navigationContent.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_article -> {
                    it.isChecked = true
                    tvTitle.text = "公众号文章"
                    getChapters()
                }
                R.id.item_project -> {
                    it.isChecked = true
                    tvTitle.text = "开源项目"
                    getProjectList()
                }
                R.id.item_about -> {
                    startActivity<AboutMeActivity>()
                }
            }
            drawParent.closeDrawers()
            true
        }
    }

    private fun getProjectList() {
        showLoading()
        mPresenter.getProjects()
    }


    override fun getProjectTypeSuccess(projectTypeList: List<ProjectType>) {
        mProjectTypes = projectTypeList as MutableList<ProjectType>
        for (position in projectTypeList.indices) {
            tabNavigation.addTab(tabNavigation.newTab())
        }
        projectPagerAdapter = ProjectPagerAdapter(supportFragmentManager,mProjectTypes)
        contentViewPager.adapter = projectPagerAdapter
        hideLoading()
    }



    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            if (drawParent.isDrawerOpen(Gravity.START)) {
                drawParent.closeDrawers()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

}
