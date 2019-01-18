package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.liu.kotlin.wanandroid.kotlinwanandroid.R
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.ProjectItem

/**
 * author: liu
 * date: 2019/1/17 9:52
 * 项目Adapter
 */
class ProjectAdapter(private val context: Context, private var mProjectList: List<ProjectItem.DatasBean>) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {
    private var mListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_project, parent, false))

    override fun getItemCount(): Int = if (mProjectList.isEmpty()) 0 else mProjectList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = mProjectList[position].title
        holder.tvDesc.text = "项目说明：${mProjectList[position].desc}"
        holder.tvAuthor.text = mProjectList[position].author
        holder.tvPublishTime.text = mProjectList[position].niceDate
        holder.itemView.setOnClickListener {
            if (mListener != null) {
                mListener!!.onItemClick(position,mProjectList[position].link!!)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_description)
        val tvAuthor: TextView = itemView.findViewById(R.id.tv_author)
        val tvPublishTime: TextView = itemView.findViewById(R.id.tv_publish_time)
    }

    fun refreshData(temList: List<ProjectItem.DatasBean>) {
        (mProjectList as MutableList).addAll(temList)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, projectUrl: String)
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.mListener = itemClickListener
    }
}