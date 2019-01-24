package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.activity

import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import com.liu.kotlin.wanandroid.kotlinwanandroid.R
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BaseMvpActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.contract.ContractUser
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.presenter.RegisterPresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.bindView
import org.jetbrains.anko.toast

/**
 * author: liu
 * date: 2019/1/15 8:12
 * 注册页
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter, ContractUser.RegisterView>(), ContractUser.RegisterView {

    private val btnRegister by bindView<Button>(R.id.btn_register)
    private val etUserName by bindView<EditText>(R.id.et_username)
    private val etPassWord by bindView<EditText>(R.id.et_password)
    private val etRePassWord by bindView<EditText>(R.id.et_repassword)

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, RegisterActivity::class.java)
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
        btnRegister.setOnClickListener {
            validateAndRegister()
        }
    }

    private fun validateAndRegister() {
        validateInfo()
    }

    private fun validateInfo() {
        var userName = etUserName.text.toString().trim()
        var password = etPassWord.text.toString().trim()
        var rePassword = etRePassWord.text.toString().trim()

        if (userName.isEmpty() ||
                password.isEmpty() || rePassword.isEmpty()) {
            toast("注册所填信息不能为空！")
        } else if (rePassword != password) {
            toast("两次输入的密码不同！")
        } else {
            registerUser(userName, password, rePassword)
        }
    }

    private fun registerUser(username: String, password: String, rePassword: String) {
        showLoading()
        mPresenter.registerUser(username, password, rePassword)
    }

    override fun registerSuccessOrFailed(errorCode: Int, msg: String) {
        toast(msg)
        hideLoading()
        if (errorCode == 0) {
            finishActivity()
        }
    }

    override fun initData() {

    }
}