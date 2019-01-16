package com.liu.kotlin.wanandroid.kotlinwanandroid.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * author: liu
 * date: 2019/1/16 13:38
 * 用来进行时间戳的转换
 */
object TimeUtil {


    fun stampToStrTime(stamp: Long): String {
        val strFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = Date(stamp)
        return strFormat.format(date)
    }
}