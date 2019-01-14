package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.presenter

import android.content.Context
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BasePresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.contract.ContractUser

/**
 * author: liu
 * date: 2019/1/14 15:52
 * 登录Presenter
 */
class LoginPresenter(val context: Context) : BasePresenter<ContractUser.LoginView>(), ContractUser.LoginModel {

    override fun login(userName: String, passWord: String) {

    }

}