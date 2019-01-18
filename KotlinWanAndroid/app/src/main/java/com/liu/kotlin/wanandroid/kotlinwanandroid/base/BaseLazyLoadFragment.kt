package com.liu.kotlin.wanandroid.kotlinwanandroid.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog

/**
 * author: liu
 * date: 2019/1/16 16:42
 * 懒加载Fragment基类
 */
abstract class BaseLazyLoadFragment : Fragment() {
    protected var mContext: Context? = null
    private var isFirstLoad = false
    protected lateinit var loadingDialog: SweetAlertDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = initView(inflater, container)

        initEvent()

        //由于setUserVisibleHint方法在onCreateView()方法之前执行，第一次创建视图的时候
        // 需要先手动加载一次数据，所以先将变量置为true
        isFirstLoad = true

        if (userVisibleHint) {    //如果Fragment可见，进行数据加载
            onLazyLoad()
            isFirstLoad = false
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mContext = activity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDialog()
    }

    private fun initDialog() {       //只有在Activity创建以后才能显示dialog
        loadingDialog = SweetAlertDialog(mContext,SweetAlertDialog.PROGRESS_TYPE)
        loadingDialog.let {
            it.progressHelper.barColor = Color.parseColor("#A5DC86")
            it.titleText = "loading"
            it.setCancelable(true)
            it.setCanceledOnTouchOutside(false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isFirstLoad = false
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isFirstLoad && isVisibleToUser) {   //视图可见并且第一次加载
            onLazyLoad()
            isFirstLoad = false
        }
    }

    /**
     * 进行数据加载
     */
    abstract fun onLazyLoad()

    /**
     * 子类初始化事件
     */
    abstract fun initEvent()

    /**
     * 子类初始化视图
     */
    abstract fun initView(inflater: LayoutInflater, container: ViewGroup?): View
}