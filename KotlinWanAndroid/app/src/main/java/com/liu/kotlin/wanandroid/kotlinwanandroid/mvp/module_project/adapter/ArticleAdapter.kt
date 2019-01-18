package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.liu.kotlin.wanandroid.kotlinwanandroid.R
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Article
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.TimeUtil
import org.jetbrains.anko.textResource

/**
 * author: liu
 * date: 2019/1/16 10:27
 * 文章列表Adapter
 */
class ArticleAdapter(private val context: Context, var articleList: List<Article.DatasBean>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_article, p0, false))

    override fun getItemCount(): Int = if (articleList.isEmpty()) 0 else articleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = articleList[position].title
        holder.tvAuthor.text = articleList[position].author
        holder.tvPublishTime.text = TimeUtil.stampToStrTime(articleList[position].publishTime)
        holder.itemView.setOnClickListener {
            if (mListener != null) {
                mListener!!.onItemClick(position,articleList[position].link!!)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvAuthor: TextView = itemView.findViewById(R.id.tv_author)
        val tvPublishTime: TextView = itemView.findViewById(R.id.tv_publish_time)
    }

    fun refreshData(temList: List<Article.DatasBean>) {
        (articleList as MutableList).addAll(temList)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, projectUrl: String)
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.mListener = itemClickListener
    }
}