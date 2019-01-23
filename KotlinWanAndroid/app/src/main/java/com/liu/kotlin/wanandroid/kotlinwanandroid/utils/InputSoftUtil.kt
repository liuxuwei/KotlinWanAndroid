package com.liu.kotlin.wanandroid.kotlinwanandroid.utils

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

/**
 * author: liu
 * date: 2019/1/23 11:03
 * description
 */
object InputSoftUtil {

    fun showOrHideKeyBord(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    fun hideInputKeyBoard(context: Context) {
        val activity = context as Activity
        activity.runOnUiThread {
            val mInputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (activity.currentFocus != null) {
                mInputManager.hideSoftInputFromWindow(activity.currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                //下边这句注释掉也可以用，暂时不知道会不会有问题
                activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
            }
        }
    }

    fun isKeyBordShowing(context: Context): Boolean {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.isActive       //返回true 代表输入法打开
    }
}