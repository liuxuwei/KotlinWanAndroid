package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.activity

import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.liu.kotlin.wanandroid.kotlinwanandroid.R
import com.liu.kotlin.wanandroid.kotlinwanandroid.base.BaseActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.DeviceUtil
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.bindView
import org.jetbrains.anko.toast

class AboutMeActivity : BaseActivity() {
    private val ivBack by bindView<ImageView>(R.id.iv_back)
    private val tvQQNum by bindView<TextView>(R.id.tv_qq_num)
    private val tvGithub by bindView<TextView>(R.id.tv_github)
    private val tvCSDNBlog by bindView<TextView>(R.id.tv_csdn_blog)
    private val layoutStatus by bindView<LinearLayout>(R.id.layout_status_bar)

    override fun getContentLayoutId(): Int {
        return R.layout.activity_about_me
    }

    override fun initView() {
        layoutStatus.setBackgroundResource(R.color.color_blue)
        DeviceUtil.setStatusBar(layoutStatus)
        setListener()
    }

    override fun initData() {}


    private fun setListener() {
        ivBack.setOnClickListener {
            finish()
        }

        tvGithub.setOnClickListener {
            DetailsActivity.start(this@AboutMeActivity, tvGithub.text.toString())
        }

        tvCSDNBlog.setOnClickListener {
            DetailsActivity.start(this@AboutMeActivity, tvCSDNBlog.text.toString())
        }

        tvQQNum.setOnClickListener {
            contactWithMe()
        }
    }

    private fun contactWithMe() {
        val qqNum = tvQQNum.text.toString()
        val url = "mqqwpa://im/chat?chat_type=wpa&uin=$qqNum"
        startQQ(url)
    }

    private fun startQQ(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: Exception) {
            toast("请先安装QQ")
        }
    }
}
