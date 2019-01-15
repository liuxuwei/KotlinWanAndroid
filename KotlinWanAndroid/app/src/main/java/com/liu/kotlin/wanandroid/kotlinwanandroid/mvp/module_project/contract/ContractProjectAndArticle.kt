package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.contract

import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IModel
import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IView
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Chapters

/**
 * author: liu
 * date: 2019/1/15 15:27
 * description
 */
interface ContractProjectAndArticle {

    interface ProjectAndArticleView : IView {
        fun getChaptersSuccess(chapterList: List<Chapters>)
    }

    interface ProjectAndArticleModel : IModel {

        fun getChapters()
    }



}