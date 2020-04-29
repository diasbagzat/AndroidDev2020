package com.example.vk_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_kotlin.AdapterLike.FragmentLikeListener
import com.example.vk_kotlin.AdapterLike.ItemClickListener

class FragmentLike : Fragment() {

    private var modelFeedList: MutableList<ModelFeed>? = null
    var recyclerView: RecyclerView? = null
    private var listener: ItemClickListener? = null
    private  var fragmentLikeListener: FragmentLikeListener? = null
    private  var adapterLike: AdapterLike? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ViewGroup {
        val rootView = inflater
            .inflate(R.layout.fragment_like, container, false) as ViewGroup
        recyclerView = rootView.findViewById(R.id.like_feed)
        recyclerView?.setLayoutManager(LinearLayoutManager(activity))
        listener = object : ItemClickListener {
             fun ItemClick(position: Int, item: ModelFeed?) {
                val intent = Intent(activity, Detail::class.java)
                intent.putExtra("news", item)
                startActivity(intent)
            }

            override fun itemClick(position: Int, item: ModelFeed?) {
                TODO("Not yet implemented")
            }
        }
        fragmentLikeListener = object : FragmentLikeListener {
            override fun removeItemLike(feed: ModelFeed?) {
                (activity as MainActivity?)!!.removeItemLike(feed)
            }
        }
        modelFeedList =  ArrayList()
        adapterLike = AdapterLike(modelFeedList as ArrayList<ModelFeed>, listener, fragmentLikeListener)
        recyclerView?.setAdapter(adapterLike)
        return rootView
    }
    fun saveNews(feed: ModelFeed) {
        modelFeedList!!.add(feed)
        recyclerView!!.adapter!!.notifyItemInserted(modelFeedList!!.size - 1)
    }

    fun removeNews(feed: ModelFeed?) {
        if (modelFeedList!!.indexOf(feed) == 0) {
            modelFeedList!!.remove(feed)
        } else {
            val position = modelFeedList!!.indexOf(feed)
            modelFeedList!!.remove(feed)
            recyclerView!!.adapter!!.notifyItemRemoved(position)
        }
    }

    fun removeLike(feed: ModelFeed) {
        val n = modelFeedList!!.indexOf(feed)
        removeNews(feed)
        adapterLike?.notifyItemRemoved(n)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) : View? {
////        super.onViewCreated(view, savedInstanceState)
//        modelFeedList =  ArrayList()
//        recyclerView = view.findViewById(R.id.like_feed)
//
//        recyclerView?.setLayoutManager(LinearLayoutManager(activity))
//        fragmentLikeListener = object : ItemClickListener, FragmentLikeListener {
//             fun ItemClick(position: Int, item: ModelFeed?) {
//                val intent = Intent(activity, Detail::class.java)
//                intent.putExtra("news", item)
//                startActivity(intent)
//            }
//
//
//            override fun itemClick(position: Int, item: ModelFeed?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun removeItemLike(feed: ModelFeed?) {
//                (activity as MainActivity?)!!.removeItemLike(feed)
//            }
//        }
//
//
//        bindViews(view)
//        initAdapter()
//
//    }
//
//    private fun initAdapter() {
//
//
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        adapterLike =
//            this.context.let {
//                AdapterLike(
//                    itemClickListener = this,
//                    fragmentLikeListener = this,
////                    feedArrayList = this.modelFeedArrayList
//
//                )
//            }
//        recyclerView.adapter = adapterLike
//    }
//
//    private fun bindViews(view: View) = with(view) {
//
//
//        recyclerView = findViewById(R.id.like_feed)
//    }
//
//    override fun itemClick(position: Int, item: ModelFeed?) {
//        val intent = Intent(context, Detail::class.java)
//        intent.putExtra("news", item)
//        intent.putExtra("like", item?.isLiked)
//        startActivity(intent)
//    }
//
//    override fun removeItemLike(feed: ModelFeed?) {
//        (context as MainActivity?)?.removeItemLike(feed)
//    }
//    fun saveNews(modelFeed: ModelFeed?) {
//        if (modelFeed != null) {
//            modelFeedArrayList.add(modelFeed)
//        }
//        recyclerView.adapter!!.notifyItemInserted(modelFeedArrayList.size - 1)
//    }
//
//    fun removeNews(modelFeed: ModelFeed?) {
//        if (modelFeedArrayList.indexOf(modelFeed) == 0) {
//            modelFeedArrayList.remove(modelFeed)
//        } else {
//            val position = modelFeedArrayList.indexOf(modelFeed)
//            modelFeedArrayList.remove(modelFeed)
//            recyclerView.adapter!!.notifyItemRemoved(position)
//        }
//    }
//
//    fun removeLike(modelFeed: ModelFeed?) {
//        val n = modelFeedArrayList.indexOf(modelFeed)
//        this.removeNews(modelFeed)
//        adapterLike.notifyItemRemoved(n)
//    }
//
}