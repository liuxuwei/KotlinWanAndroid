package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.contract

import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IModel
import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IView
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Article
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Chapters
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.ProjectItem
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.ProjectType

/**
 * author: liu
 * date: 2019/1/15 15:27
 * description
 */
interface ContractProjectAndArticle {

    /*-------------------公众号---------------------*/

    interface ChapterTypeView : IView {
        fun getProjectTypeSuccess(projectTypeList: List<ProjectType>)

        fun getChaptersSuccess(chapterList: List<Chapters>)

        fun getProjectOrChaptersFailed(msg: String)
    }

    interface ChapterTypeModel : IModel {

        fun getChapters()

        fun getProjects()
    }

    /*---------------------文章------------------*/

    interface ArticleView : IView {
        fun getArticleListSuccess(dataList: List<Article.DatasBean>)

        fun searchArticleSuccess(dataList: List<Article.DatasBean>)

        fun getOrSearchFailed(msg: String)
    }

    interface ArticleModel : IModel {
        fun getArticleList(chapterId: Int, page: Int)

        fun searchArticle(chapterId: Int, page: Int, keyWords: String)
    }

    /*----------------------项目------------------*/
    interface ProjectView : IView {
        fun getProjectListSuccess(dataList: List<ProjectItem.DatasBean>)
    }

    interface ProjectModel : IModel {
        fun getProjectList(typeId: Int, page: Int)
    }

    /*-----------------------详情---------------------*/
    interface DetailsView : IView {

    }

    interface DetailsModel : IModel {

    }


}