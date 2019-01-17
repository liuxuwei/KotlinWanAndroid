package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.liu.kotlin.wanandroid.kotlinwanandroid.R
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Article
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BaseMvpFragment
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.adapter.ArticleAdapter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.contract.ContractProjectAndArticle
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.presenter.ArticleFragPresenter
import org.jetbrains.anko.support.v4.toast

/**
 * author: liu
 * date: 2019/1/15 16:57
 * 文章列表Fragment
 */
class ArticleFragment : BaseMvpFragment<ArticleFragPresenter,ContractProjectAndArticle.ArticleView>(),ContractProjectAndArticle. ArticleView{
    private lateinit var rcyArticle: RecyclerView
    private lateinit var tvSearchBtn: TextView
    private lateinit var etSearchView: EditText
    private var articleList: MutableList<Article.DatasBean> = mutableListOf()
    private lateinit var articleAdapter: ArticleAdapter
    private var chapterId = 0
    private var mPosition = 0
    private var page = 1

    companion object {
        fun newInstance(position: Int, chapterId: Int): ArticleFragment {
            val fragment = ArticleFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            bundle.putInt("id", chapterId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getPresenter(): ArticleFragPresenter {
        return ArticleFragPresenter(activity as Context)
    }

    override fun init() {
        if (arguments != null) {
            chapterId = arguments!!.getInt("id")
            mPosition = arguments!!.getInt("position")
        }
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?): View {
        val view = inflater.inflate(R.layout.fragment_article,container,false)
        tvSearchBtn = view.findViewById(R.id.tv_btn_search)
        etSearchView = view.findViewById(R.id.et_search)
        rcyArticle = view.findViewById(R.id.rcy_article)
        articleAdapter = ArticleAdapter(view.context, articleList)
        rcyArticle.layoutManager = LinearLayoutManager(view.context)
        rcyArticle.adapter = articleAdapter
        setListener()
        return view
    }

    private fun setListener() {
        tvSearchBtn.setOnClickListener {
            toast("搜索")
            showLoading()
        }
        etSearchView.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                //todo search article in current chapter
            }
            false
        }
    }


    override fun onLazyLoad() {
        getArticleList()
    }

    private fun getArticleList() {
        mPresenter.getArticleList(chapterId,page)
    }

    override fun getArticleListSuccess(dataList: List<Article.DatasBean>) {
        articleList = dataList as MutableList
        articleAdapter.refreshData(articleList)
        hideLoading()
    }
}