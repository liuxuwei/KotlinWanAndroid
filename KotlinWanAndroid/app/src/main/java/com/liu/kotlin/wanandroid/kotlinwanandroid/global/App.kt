package com.liu.kotlin.wanandroid.kotlinwanandroid.global

import android.app.Application
import android.content.res.Configuration
import android.content.res.Resources
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.User
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 * author: liu
 * date: 2019/1/14 11:26
 * 全局Application
 */
class App : Application() {
    lateinit var user: User

    companion object {
        @JvmStatic
        private lateinit var instance: App

        fun getInstance(): App {
            return instance
        }
    }


    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
        instance = this
//        user = User(this)
    }

    /**
     * 防止字体随系统字体调整而变化
     */
    override fun getResources(): Resources {
        val resources = super.getResources()
        val configuration = Configuration()
        configuration.setToDefaults()
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return super.getResources()
    }



}