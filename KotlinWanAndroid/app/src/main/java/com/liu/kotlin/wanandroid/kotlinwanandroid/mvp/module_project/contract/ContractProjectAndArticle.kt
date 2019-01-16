package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.contract

import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IModel
import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IView
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Article
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Chapters

/**
 * author: liu
 * date: 2019/1/15 15:27
 * description
 */
interface ContractProjectAndArticle {

    /*-------------------公众号---------------------*/
    interface ChapterTypeView : IView {
        fun getChaptersSuccess(chapterList: List<Chapters>)
    }

    interface ChapterTypeModel : IModel {

        fun getChapters()
    }

    /*---------------------文章------------------*/

    interface ArticleView : IView {
        fun getArticleListSuccess(dataList: List<Article.DatasBean>)
    }

    interface ArticleModel : IModel {
        fun getArticleList(chapterId: Int,page: Int)
    }



}