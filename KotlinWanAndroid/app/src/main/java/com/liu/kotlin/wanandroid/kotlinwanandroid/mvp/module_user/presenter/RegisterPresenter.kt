package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.presenter

import android.content.Context
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BasePresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.contract.ContractUser

/**
 * author: liu
 * date: 2019/1/15 8:13
 * description
 */
class RegisterPresenter(val context: Context): BasePresenter<ContractUser.RegisterView>(),ContractUser.RegisterModel {


}