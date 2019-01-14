package com.liu.kotlin.wanandroid.kotlinwanandroid.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * author: liu
 * date: 2019/1/14 11:28
 * BaseActivity
 */
abstract class BaseActivity : AppCompatActivity(){

    abstract fun getContentLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentLayoutId())
        initView()
        initData()
    }

    abstract fun initView()

    abstract fun initData()



}
