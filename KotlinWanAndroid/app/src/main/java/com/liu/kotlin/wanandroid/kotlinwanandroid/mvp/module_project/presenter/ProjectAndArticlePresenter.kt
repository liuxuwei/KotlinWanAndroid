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
 * date: 2019/1/15 15:29
 * description
 */
class ProjectAndArticlePresenter(val context: Context): BasePresenter<ContractProjectAndArticle.ChapterTypeView>(),ContractProjectAndArticle.ChapterTypeModel {


    /**
     * 获取项目分类
     */
    override fun getProjects() {
        RetrofitHelper.getInstance()
                .create(ApiService::class.java)
                .getProjectType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.errorCode == 0) {
                        mRootView.getProjectTypeSuccess(it.data)
                    }
                }
    }

    /**
     * 获取公众号列表
     */
    override fun getChapters() {
        RetrofitHelper.getInstance()
                .create(ApiService::class.java)
                .getChapters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.errorCode == 0) {
                        mRootView.getChaptersSuccess(it.data)
                    }
                }
    }



}