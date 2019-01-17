package com.liu.kotlin.wanandroid.kotlinwanandroid.utils

import android.os.Build
import android.view.View
import android.widget.LinearLayout
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.App

/**
 * author: liu
 * date: 2019/1/17 15:02
 * description
 */
object DeviceUtil {

    private var statusBarHeight = 0

    fun setStatusBar(layout: LinearLayout){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val statusHeight = getStatusBarHeight()
            val linearParams = layout.layoutParams as LinearLayout.LayoutParams
            linearParams.height = statusHeight
            layout.layoutParams = linearParams
        } else {
            layout.visibility = View.GONE
        }
    }

    fun getStatusBarHeight() : Int{
        if (statusBarHeight == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                var statusHeight = 0
                val resId = App.getInstance().resources.getIdentifier("status_bar_height", "dimen", "android")
                if (resId > 0) {
                    statusHeight = App.getInstance().resources.getDimensionPixelSize(resId)
                }
                statusBarHeight = statusHeight
                return statusHeight
            } else {
                return -1
            }
        } else {
            return statusBarHeight
        }
    }
}