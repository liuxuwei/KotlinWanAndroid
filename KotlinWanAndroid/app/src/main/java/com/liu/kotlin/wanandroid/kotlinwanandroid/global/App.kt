package com.liu.kotlin.wanandroid.kotlinwanandroid.global

import android.app.Application
import android.content.res.Configuration
import android.content.res.Resources

/**
 * author: liu
 * date: 2019/1/14 11:26
 * 全局Application
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    /**
     * 防止字体随系统字体调整而变化
     */
    override fun getResources(): Resources {
        val resources = super.getResources()
        val configuration = Configuration()
        configuration.setToDefaults()
        resources.updateConfiguration(configuration,resources.displayMetrics)
        return super.getResources()
    }
}