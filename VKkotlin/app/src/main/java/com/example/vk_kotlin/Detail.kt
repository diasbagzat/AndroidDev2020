package com.example.vk_kotlin

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Detail : AppCompatActivity() {

    var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val proPic = findViewById<ImageView>(R.id.iv_proPic_detail)
        val postPic = findViewById<ImageView>(R.id.iv_postPic_detail)
        val like = findViewById<ImageView>(R.id.likeBtn)
        val likes: Boolean
        val tvNameDetail = findViewById<TextView>(R.id.tv_name_detail)
        val tvTimeDetail = findViewById<TextView>(R.id.tv_time_detail)
        val tvStatusDetail = findViewById<TextView>(R.id.tv_status_detail)
        val tvLikeDetail = findViewById<TextView>(R.id.tv_like_detail)
        likes = intent.extras?.getBoolean("like")!!
        val tvCommentDetail = findViewById<TextView>(R.id.tv_comment_detail)
        val tvRepostDetail = findViewById<TextView>(R.id.tv_repost_detail)
        val tvViewDetail = findViewById<TextView>(R.id.tv_view_detail)
        val modelFeed = intent.getSerializableExtra("news") as ModelFeed

            proPic?.setImageResource(modelFeed.propic)
            postPic?.setImageResource(modelFeed.postpic)
            tvNameDetail?.text = modelFeed.name
            tvTimeDetail?.text = modelFeed.time
            tvStatusDetail?.text = modelFeed.status
            tvLikeDetail?.text = (modelFeed.likes).toString()
            tvCommentDetail?.text = modelFeed.commments
            tvRepostDetail?.text = modelFeed.repost
            tvViewDetail?.text = modelFeed.view
            modelFeed.isLiked = likes
            like.setOnClickListener(View.OnClickListener {
                if (!isLiked) {
                    modelFeed.isLiked = true
                    modelFeed.likes = modelFeed.likes + 1
                    tvLikeDetail.text = (modelFeed.likes).toString()
                    like.setImageResource(R.drawable.liked)
                } else {
                    like.setImageResource(R.drawable.like)
                    modelFeed.isLiked = false
                    modelFeed.likes = modelFeed.likes - 1
                    tvLikeDetail.text = (modelFeed.likes).toString()

                }

            })

            if (isLiked) {
                like.setImageResource(R.drawable.liked)
            } else like.setImageResource(R.drawable.like)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = "Детали"


        }
    }
