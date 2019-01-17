package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.presenter

import android.content.Context
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.ApiService
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.RetrofitHelper
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BasePresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.contract.ContractProjectAndArticle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * author: liu
 * date: 2019/1/17 9:21
 * description
 */
class ProjectFragPresenter(val context: Context): BasePresenter<ContractProjectAndArticle.ProjectView>(),ContractProjectAndArticle.ProjectModel {


    override fun getProjectList(typeId: Int, page: Int) {
        RetrofitHelper.getInstance()
                .create(ApiService::class.java)
                .getProjectList(page,typeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.errorCode == 0) {
                        mRootView.getProjectListSuccess(it.data.datas!!)
                    }
                }
    }
}