package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.liu.kotlin.wanandroid.kotlinwanandroid.R
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.ProjectItem
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BaseMvpFragment
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.activity.DetailsActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.adapter.ProjectAdapter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.contract.ContractProjectAndArticle
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.presenter.ProjectFragPresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.loadsir.LoadingCallback
import com.scwang.smartrefresh.header.DeliveryHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import org.jetbrains.anko.support.v4.toast

/**
 * author: liu
 * date: 2019/1/17 9:21
 * 项目列表Fragment
 */
class ProjectFragment : BaseMvpFragment<ProjectFragPresenter, ContractProjectAndArticle.ProjectView>(), ContractProjectAndArticle.ProjectView {
    private var mPosition = 0
    private var typeId = 0
    private var page = 1
    private var isLoadMore = false
    private lateinit var loadService: LoadService<*>
    private lateinit var rcyProject: RecyclerView
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var projectAdapter: ProjectAdapter
    private var projectList: MutableList<ProjectItem.DatasBean> = mutableListOf()


    companion object {
        fun newInstance(position: Int, typeId: Int): ProjectFragment {
            val fragment = ProjectFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            bundle.putInt("typeId", typeId)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getPresenter(): ProjectFragPresenter {
        return ProjectFragPresenter(activity as Context)
    }

    override fun init() {
        if (arguments != null) {
            typeId = arguments!!.getInt("typeId")
            mPosition = arguments!!.getInt("position")
        }
        loadService = LoadSir.getDefault().register(refreshLayout) {
            loadService.showCallback(LoadingCallback::class.java)
            getProjectList()
        }
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?): View {
        val contentView = inflater.inflate(R.layout.fragment_project, container, false)
        rcyProject = contentView.findViewById(R.id.rcy_project)
        refreshLayout = contentView.findViewById(R.id.smart_project_refresh)

        initAdapter()

        initRefreshLayout()

        return contentView
    }

    private fun initAdapter() {
        projectAdapter = ProjectAdapter(activity as Context, projectList)
        projectAdapter.setOnItemClickListener(object : ProjectAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, projectUrl: String) {
                DetailsActivity.start(context!!, projectUrl)
            }
        })
        rcyProject.layoutManager = LinearLayoutManager(activity)
        rcyProject.adapter = projectAdapter
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
                getProjectList()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 1
                getProjectList()
            }

        })
    }

    override fun onLazyLoad() {
        getProjectList()
    }

    private fun getProjectList() {
        if (!isLoadMore) {
            loadService.showCallback(LoadingCallback::class.java)
        }
        mPresenter.getProjectList(typeId, page)
    }

    override fun getProjectListSuccess(dataList: List<ProjectItem.DatasBean>) {
        projectList = dataList as MutableList
        if (isLoadMore && projectList.isEmpty()) {
            toast("没有更多数据啦！")
            if (page > 1) {
                page--
            }
        }
        if (isLoadMore) {
            projectAdapter.loadMoreData(projectList)
            refreshLayout.finishLoadMore()
            isLoadMore = false
        } else {
            refreshLayout.finishRefresh()
            loadService.showSuccess()
            projectAdapter.refreshData(projectList)
        }
    }
}