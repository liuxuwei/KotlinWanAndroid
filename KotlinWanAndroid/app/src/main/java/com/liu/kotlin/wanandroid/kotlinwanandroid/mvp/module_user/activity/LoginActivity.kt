package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.activity

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.liu.kotlin.wanandroid.kotlinwanandroid.R
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BaseMvpActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.activity.ProjectAndArticleActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.contract.ContractUser
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.presenter.LoginPresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.bindView
import org.jetbrains.anko.toast

/**
 * author: liu
 * date: 2019/1/14 15:51
 * 登录Activity
 */
class LoginActivity : BaseMvpActivity<LoginPresenter, ContractUser.LoginView>(),ContractUser.LoginView {

    private var exitTime: Long = 0

    private val tvRegister by bindView<TextView>(R.id.tv_register)
    private val btnLogin by bindView<Button>(R.id.btn_login)
    private val etUserName by bindView<EditText>(R.id.et_username)
    private val etPassWord by bindView<EditText>(R.id.et_password)

    companion object {
        fun start(context: Context) {
            val intent = Intent()
            intent.setClass(context,LoginActivity::class.java)
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
        btnLogin.setOnClickListener {
            showLoading()
            mPresenter.login(etUserName.text.toString(),etPassWord.text.toString())
        }

        tvRegister.setOnClickListener {
            toast("注册")
        }
    }

    override fun initData() {

    }

    override fun showLoginSuccess(username: String) {
        hideLoading()
        ProjectAndArticleActivity.start(getContext())
        toast("Hello $username")
    }



    override fun onBackPressed() {

        super.onBackPressed()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                toast("再按一次退出程序")
                exitTime = System.currentTimeMillis()
            } else {
                finishActivity()
                System.exit(0)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}