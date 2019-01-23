package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.activity

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.liu.kotlin.wanandroid.kotlinwanandroid.R
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BaseMvpActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.activity.ProjectAndArticleActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.contract.ContractUser
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.presenter.LoginPresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.DeviceUtil
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.bindView
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.toast

/**
 * author: liu
 * date: 2019/1/14 15:51
 * 登录Activity
 */
class LoginActivity : BaseMvpActivity<LoginPresenter, ContractUser.LoginView>(), ContractUser.LoginView {

    private val tvRegister by bindView<TextView>(R.id.tv_register)
    private val btnLogin by bindView<Button>(R.id.btn_login)
    private val etUserName by bindView<EditText>(R.id.et_username)
    private val etPassWord by bindView<EditText>(R.id.et_password)
    private val llQQLogin by bindView<LinearLayout>(R.id.ll_qq_login)
    private val layoutStatus by bindView<LinearLayout>(R.id.layout_status_bar)
    private val llWeChatLogin by bindView<LinearLayout>(R.id.ll_wechat_login)

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }


    override fun getContentLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getPresenter(): LoginPresenter {
        return LoginPresenter(getContext())
    }

    override fun init() {
        DeviceUtil.setStatusBar(layoutStatus)

        btnLogin.setOnClickListener {
            checkAndLogin()
        }

        tvRegister.setOnClickListener {
            RegisterActivity.start(getContext())
        }

        llQQLogin.setOnClickListener {
            toast("程序猿还在加班赶制中~")
        }

        llWeChatLogin.setOnClickListener {
            toast("程序猿还在加班赶制中~")
        }
    }

    private fun checkAndLogin() {
        if (etPassWord.text.toString().trim().isEmpty() || etUserName.text.toString().trim().isEmpty()) {
            toast("用户名或密码不能为空")
        } else {
            showLoading()
            mPresenter.login(etUserName.text.toString().trim(), etPassWord.text.toString().trim())
        }
    }

    override fun initData() {

    }

    override fun showLoginSuccess(username: String) {
        hideLoading()
        toast("Hello $username")
        EventBus.getDefault().post(username)
        finish()
    }

    override fun showLoginFailed(msg: String) {
        hideLoading()
        toast(msg)
    }


}