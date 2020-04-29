package com.example.vk_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

 class AdapterLike (
    feedArrayList: MutableList<ModelFeed>,
    itemClickListener: ItemClickListener?,
    fragmentLikeListener: FragmentLikeListener?
) :
    RecyclerView.Adapter<AdapterLike.MyViewHolder>() {
    var feedArrayList: MutableList<ModelFeed>
    private val listener: ItemClickListener?
    private val fragmentLikeListener: FragmentLikeListener?

    init {
        this.feedArrayList = feedArrayList

        this.listener = itemClickListener
        this.fragmentLikeListener = fragmentLikeListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vk_feed, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val modelFeed  = feedArrayList[position]
        holder.ivProPic.setImageResource(modelFeed.propic)
        holder.tvName.text = modelFeed.name
        holder.tvTime.text = modelFeed.time
        holder.tvStatus.text = modelFeed.status
        holder.tvLike.text = (modelFeed.likes).toString()
        holder.tvComment.text = modelFeed.commments
        holder.tvRepost.text = modelFeed.repost
        holder.tvView.text = modelFeed.view
        holder.ivLike.setImageResource(R.drawable.liked)
        if(modelFeed.postpic == 0) {
            holder.ivPostPic.visibility = View.GONE
        } else {
            holder.ivPostPic.visibility = View.VISIBLE
            holder.ivPostPic.setImageResource(modelFeed.postpic)

        }
        holder.itemView.setOnClickListener{
            listener?.  itemClick(position, modelFeed)
        }

        holder.ivLike.setOnClickListener{
            fragmentLikeListener?.removeItemLike(modelFeed)
        }

        if (modelFeed.isLiked) {
            holder.ivLike.setImageResource(R.drawable.liked)

        } else holder.ivLike.setImageResource(R.drawable.like)
    }
    override fun getItemCount(): Int {
        return feedArrayList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var tvTime: TextView
        var tvStatus: TextView
        var tvLike: TextView
        var tvComment: TextView
        var tvRepost: TextView
        var tvView: TextView
        var ivProPic: ImageView
        var ivPostPic: ImageView
        var ivLike: ImageView

        init {
            ivPostPic = itemView.findViewById(R.id.iv_postPic)
            ivProPic = itemView.findViewById(R.id.iv_proPic)
            ivLike = itemView.findViewById(R.id.iv_like)
            tvName = itemView.findViewById(R.id.tv_name)
            tvTime = itemView.findViewById(R.id.tv_time)
            tvStatus = itemView.findViewById(R.id.tv_status)
            tvLike = itemView.findViewById(R.id.tv_like)
            tvComment = itemView.findViewById(R.id.tv_comment)
            tvRepost = itemView.findViewById(R.id.tv_repost)
            tvView = itemView.findViewById(R.id.tv_view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    interface ItemClickListener {
        fun itemClick(position: Int, item: ModelFeed?)
    }

    interface FragmentLikeListener {
        fun removeItemLike(feed: ModelFeed?)
    }

}