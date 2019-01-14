package com.liu.kotlin.wanandroid.kotlinwanandroid.base

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog

/**
 * author: liu
 * date: 2019/1/14 11:28
 * BaseActivity
 */
abstract class BaseActivity: AppCompatActivity(){

    protected lateinit var loadingDialog: SweetAlertDialog
    private lateinit var context:Context

    abstract fun getContentLayoutId(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentLayoutId())
        initDialog()
        context = this

        initView()
        initData()
    }

    private fun initDialog() {
        loadingDialog = SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE)
        loadingDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        loadingDialog.titleText = "loading"
        loadingDialog.setCancelable(true)
        loadingDialog.setCanceledOnTouchOutside(false)
    }

    abstract fun initView()

    abstract fun initData()

    fun getContext(): Context {
        return context
    }


    override fun startActivityForResult(intent: Intent, requestCode: Int, options: Bundle?) {
        if (startActivitySelfCheck(intent)) {
            // 查看源码得知 startActivity 最终也会调用 startActivityForResult
            super.startActivityForResult(intent, requestCode, options)
        }
    }

    private var mActivityJumpTag: String? = null
    private var mActivityJumpTime: Long = 0

    /**
     * 检查当前 Activity 是否重复跳转了，不需要检查则重写此方法并返回 true 即可
     *
     * @param intent          用于跳转的 Intent 对象
     * @return                检查通过返回true, 检查不通过返回false
     */
    private fun startActivitySelfCheck(intent: Intent): Boolean {
        // 默认检查通过
        var result = true
        // 标记对象
        val tag: String?
        if (intent.component != null) { // 显式跳转
            tag = intent.component.className
        } else if (intent.action != null) { // 隐式跳转
            tag = intent.action
        } else {
            return result
        }

        if (tag == mActivityJumpTag && mActivityJumpTime >= SystemClock.uptimeMillis() - 500) {
            // 检查不通过
            result = false
        }

        // 记录启动标记和时间
        mActivityJumpTag = tag
        mActivityJumpTime = SystemClock.uptimeMillis()
        return result
    }



}
