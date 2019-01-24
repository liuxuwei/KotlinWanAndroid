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
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.liu.kotlin.wanandroid.kotlinwanandroid.R
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Article
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BaseMvpFragment
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.activity.DetailsActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.adapter.ArticleAdapter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.contract.ContractProjectAndArticle
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.presenter.ArticleFragPresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.MyItemDecoration
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.loadsir.EmptyDataCallback
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.loadsir.LoadingCallback
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.loadsir.ServerErrorCallback
import com.scwang.smartrefresh.header.DeliveryHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import org.jetbrains.anko.support.v4.toast

/**
 * author: liu
 * date: 2019/1/15 16:57
 * 文章列表Fragment
 */
class ArticleFragment : BaseMvpFragment<ArticleFragPresenter, ContractProjectAndArticle.ArticleView>(), ContractProjectAndArticle.ArticleView {

    private var page = 1
    private var chapterId = 0
    private var mPosition = 0

    private lateinit var rcyArticle: RecyclerView
    private lateinit var tvSearchBtn: TextView
    private lateinit var etSearchView: EditText
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var loadService: LoadService<*>

    private var isLoadMore = false
    private lateinit var articleAdapter: ArticleAdapter
    private var articleList: MutableList<Article.DatasBean> = mutableListOf()

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
        loadService = LoadSir.getDefault().register(refreshLayout) {
            loadService.showCallback(LoadingCallback::class.java)
            getArticleList()
        }
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?): View {
        val view = inflater.inflate(R.layout.fragment_article, container, false)
        tvSearchBtn = view.findViewById(R.id.tv_btn_search)
        etSearchView = view.findViewById(R.id.et_search)
        rcyArticle = view.findViewById(R.id.rcy_article)
        refreshLayout = view.findViewById(R.id.smart_article_refresh)
        articleAdapter = ArticleAdapter(view.context, articleList)
        rcyArticle.addItemDecoration(MyItemDecoration())
        rcyArticle.layoutManager = LinearLayoutManager(view.context)
        rcyArticle.adapter = articleAdapter
        initRefreshLayout()
        setListener()
        return view
    }

    private fun initRefreshLayout() {
        refreshLayout.setRefreshHeader(DeliveryHeader(activity as Context))
        refreshLayout.setRefreshFooter(BallPulseFooter(activity as Context))
        refreshLayout.setDragRate(0.6f)
        refreshLayout.setHeaderTriggerRate(0.4f)
        refreshLayout.setReboundDuration(1000)
        refreshLayout.setEnableAutoLoadMore(false)
        refreshLayout.setEnableLoadMore(true)

        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                isLoadMore = true
                page++
                getArticleList()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 1
                getArticleList()
            }

        })
    }

    private fun setListener() {

        articleAdapter.setOnItemClickListener(object : ArticleAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, projectUrl: String) {
                DetailsActivity.start(activity as Context, projectUrl)
            }

        })

        tvSearchBtn.setOnClickListener {
            searchArticle()
        }
        etSearchView.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                searchArticle()
            }
            false
        }
    }

    private fun searchArticle() {
        if (etSearchView.text.toString().isEmpty() || etSearchView.text.toString().isBlank()) {
            toast("请输入要搜索的内容")
        } else {
            mPresenter.searchArticle(chapterId, page, etSearchView.text.toString())
            loadService.showCallback(LoadingCallback::class.java)
        }
    }

    override fun searchArticleSuccess(dataList: List<Article.DatasBean>) {
        if (dataList.isEmpty()) {
            loadService.showCallback(EmptyDataCallback::class.java)
        } else {
            loadService.showSuccess()
            articleAdapter.refreshData(dataList)
        }
    }

    override fun onLazyLoad() {
        getArticleList()
    }

    private fun getArticleList() {
        if (!isLoadMore) {
            loadService.showCallback(LoadingCallback::class.java)
        }
        mPresenter.getArticleList(chapterId, page)
    }

    override fun getArticleListSuccess(dataList: List<Article.DatasBean>) {
        articleList = dataList as MutableList
        if (isLoadMore && articleList.isEmpty()) {
            toast("没有更多数据啦！")
            if (page > 1) {
                page--
            }
        }
        if (isLoadMore) {
            articleAdapter.loadMoreData(articleList)
            refreshLayout.finishLoadMore()
            isLoadMore = false
        } else {
            articleAdapter.refreshData(articleList)
            refreshLayout.finishRefresh()
            loadService.showSuccess()
        }

        hideLoading()
    }

    override fun getOrSearchFailed(msg: String) {
        toast(msg)
        hideLoading()
        loadService.showCallback(ServerErrorCallback::class.java)
    }
}