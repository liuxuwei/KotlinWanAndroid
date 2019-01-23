package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.presenter

import android.content.Context
import com.liu.kotlin.wanandroid.kotlinwanandroid.base.BaseObserver
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Chapters
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.ProjectType
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.ApiService
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.RetrofitHelper
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BasePresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.contract.ContractProjectAndArticle
import com.orhanobut.logger.Logger
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
                .subscribe(object: BaseObserver<List<ProjectType>>(){
                    override fun onSuccess(bean: List<ProjectType>) {
                        mRootView.getProjectTypeSuccess(bean)
                    }

                    override fun onFailed(msg: String) {
                        mRootView.getProjectOrChaptersFailed(msg)
                    }

                })
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
                .subscribe(object : BaseObserver<List<Chapters>>(){
                    override fun onSuccess(bean: List<Chapters>) {
                        mRootView.getChaptersSuccess(bean)
                    }

                    override fun onFailed(msg: String) {
                        mRootView.getProjectOrChaptersFailed(msg)
                    }

                })


    }



}