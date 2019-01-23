package com.liu.kotlin.wanandroid.kotlinwanandroid.utils.loadsir

import android.content.Context
import android.net.ConnectivityManager
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.App

/**
 * author: liu
 * date: 2019/1/23 13:38
 * 判断网络
 */
object NetWorkUtils {

    fun isNetWorkAvailable(instance: App): Boolean {
        val connectActivity = instance.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectActivity != null) {
            val info = connectActivity.activeNetworkInfo
            if (info != null && info.isConnected) {
                //当前网络可用
                return true
            }
        }
        return false
    }
}