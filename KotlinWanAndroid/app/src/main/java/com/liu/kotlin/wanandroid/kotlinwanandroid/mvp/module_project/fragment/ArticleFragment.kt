package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liu.kotlin.wanandroid.kotlinwanandroid.R

/**
 * author: liu
 * date: 2019/1/15 16:57
 * description
 */
class ArticleFragment : Fragment(){

    companion object {
        fun newInstance(): ArticleFragment {
            return ArticleFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article,container,false)
    }
}