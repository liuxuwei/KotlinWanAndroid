package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.presenter

import android.content.Context
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.ApiService
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.RetrofitHelper
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BasePresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.contract.ContractProjectAndArticle
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * author: liu
 * date: 2019/1/16 17:38
 * description
 */
class ArticleFragPresenter(val context: Context): BasePresenter<ContractProjectAndArticle.ArticleView>(),ContractProjectAndArticle.ArticleModel {
    override fun getArticleList(chapterId: Int, page: Int) {
        RetrofitHelper.getInstance()
                .create(ApiService::class.java)
                .getArticles(chapterId,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.errorCode == 0) {
                        if (it.data.datas!!.isNotEmpty()) {
                            mRootView.getArticleListSuccess(it.data.datas!!)
                        }
                    }
                }
    }


}