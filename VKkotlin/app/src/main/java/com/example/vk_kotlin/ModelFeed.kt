package com.example.vk_kotlin

import  java.io.Serializable
import java.util.*

data class ModelFeed(
    var id: Int,
    var view: String,
    var repost: String,
    var likes: Int,
    var commments: String,
    var propic: Int,
    var postpic: Int,
    var name: String,
    var time: String,
    var status: CharSequence
) :
    Serializable {
    var isLiked = false

    companion object {
        var modelFeedArrayList: MutableList<ModelFeed> = ArrayList()
    }
}