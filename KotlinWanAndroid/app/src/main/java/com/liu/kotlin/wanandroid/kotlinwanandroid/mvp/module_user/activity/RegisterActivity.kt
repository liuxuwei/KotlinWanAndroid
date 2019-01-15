package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.activity

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.liu.kotlin.wanandroid.kotlinwanandroid.R
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BaseMvpActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.contract.ContractUser
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.presenter.RegisterPresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.bindView

/**
 * author: liu
 * date: 2019/1/15 8:12
 * description
 */
class RegisterActivity: BaseMvpActivity<RegisterPresenter,ContractUser.RegisterView>() {
    private val btnRegister by bindView<Button>(R.id.btn_register)
    private val etUserName by bindView<EditText>(R.id.et_username)
    private val etPassWord by bindView<EditText>(R.id.et_password)
    private val etRePassWord by bindView<EditText>(R.id.et_repassword)

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context,RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun getPresenter(): RegisterPresenter {
        return RegisterPresenter(getContext())
    }

    override fun init() {

    }

    override fun initData() {

    }
}