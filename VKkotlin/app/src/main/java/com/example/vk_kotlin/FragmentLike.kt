package com.example.vk_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_kotlin.AdapterLike.FragmentLikeListener
import com.example.vk_kotlin.AdapterLike.ItemClickListener

class FragmentLike : Fragment(), ItemClickListener, FragmentLikeListener {

    lateinit var modelFeedArrayList: MutableList<ModelFeed>
    lateinit var recyclerView: RecyclerView
    private lateinit var fragmentLikeListener: FragmentLikeListener
    private lateinit var adapterLike: AdapterLike

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_like, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        modelFeedArrayList =  ArrayList()


        bindViews(view)
        initAdapter()

    }

    private fun initAdapter() {


        recyclerView.layoutManager = LinearLayoutManager(context)
        adapterLike =
            this.context.let {
                AdapterLike(
                    itemClickListener = this,
                    fragmentLikeListener = this,
                    feedArrayList = this.modelFeedArrayList

                )
            }
        recyclerView.adapter = adapterLike
    }

    private fun bindViews(view: View) = with(view) {


        recyclerView = findViewById(R.id.like_feed)
    }

    override fun itemClick(position: Int, item: ModelFeed?) {
        val intent = Intent(context, Detail::class.java)
        intent.putExtra("news", item)
        intent.putExtra("like", item?.isLiked)
        startActivity(intent)
    }

    override fun removeItemLike(feed: ModelFeed?) {
        (context as MainActivity?)?.removeItemLike(feed)
    }
    fun saveNews(modelFeed: ModelFeed?) {
        if (modelFeed != null) {
            modelFeedArrayList.add(modelFeed)
        }
        recyclerView.adapter!!.notifyItemInserted(modelFeedArrayList.size - 1)
    }

    fun removeNews(modelFeed: ModelFeed?) {
        if (modelFeedArrayList.indexOf(modelFeed) == 0) {
            modelFeedArrayList.remove(modelFeed)
        } else {
            val position = modelFeedArrayList.indexOf(modelFeed)
            modelFeedArrayList.remove(modelFeed)
            recyclerView.adapter!!.notifyItemRemoved(position)
        }
    }

    fun removeLike(modelFeed: ModelFeed?) {
        val n = modelFeedArrayList.indexOf(modelFeed)
        this.removeNews(modelFeed)
        adapterLike.notifyItemRemoved(n)
    }

}