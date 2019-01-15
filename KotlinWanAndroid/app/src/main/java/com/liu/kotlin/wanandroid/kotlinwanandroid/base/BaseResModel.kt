package com.liu.kotlin.wanandroid.kotlinwanandroid.base

/**
 * author: liu
 * date: 2019/1/15 9:21
 * description
 */
data class BaseResModel<T>(var errorCode: Int, var errorMsg: String, var data: T)