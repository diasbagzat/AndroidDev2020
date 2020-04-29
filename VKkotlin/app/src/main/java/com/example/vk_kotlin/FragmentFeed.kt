package com.example.vk_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vk_kotlin.AdapterFeed.*
import java.util.*

class FragmentFeed: Fragment(), ItemClickListener, FragmentButtonListener, FragmentLikeListener {
    var isLiked = false
    lateinit var recyclerView: RecyclerView
    lateinit var listener: ItemClickListener
    private var fragmentButtonListener: FragmentButtonListener? = null
    private var fragmentLikeListener: FragmentLikeListener? = null
    private lateinit var adapterFeed: AdapterFeed

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews(view)
        initAdapter()

    }

    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapterFeed =
            this.context.let {
                AdapterFeed(
                    itemClickListener = this,
                    fragmentButtonListener = this,
                    fragmentLikeListener = this,
                    feedArrayList = newsGenerator()
                )
            }
        recyclerView.adapter = adapterFeed
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20)
    }

    private fun bindViews(view: View) = with(view){
        recyclerView = findViewById(R.id.recy_feed)
    }


    override fun myClick(modelFeed: ModelFeed?, option: Int) {
        (context as MainActivity?)?.myClick(modelFeed, option)
    }

    override fun removeItemLike(feed: ModelFeed?) {
        (context as MainActivity?)?.removeItemLike(feed)
    }

        override fun itemClick(position: Int, item: ModelFeed?) {
            val intent = Intent(context, Detail::class.java)
            intent.putExtra("news", item)
            intent.putExtra("like", item?.isLiked)
            startActivity(intent)
        }

        override fun likeClick(position: Int, item: ModelFeed?) {
            isLiked = item!!.isLiked
             if (!isLiked) {
                item.isLiked = true
                item.likes = item.likes + 1
                fragmentButtonListener?.myClick(item, 1)
               isLiked = true
            } else {
                item.isLiked = false
                 item.likes = item.likes - 1
                fragmentLikeListener?.removeItemLike(item)
                isLiked = false
            }
            adapterFeed.notifyItemChanged(position)
        }



    private fun newsGenerator(): MutableList<ModelFeed> {
        val items: MutableList<ModelFeed> = ArrayList<ModelFeed>()
        val feed1 = ModelFeed(
            20,
            "375",
            "63",
            274,
            "178",
            R.drawable.prof20,
            R.drawable.content20,
            "Как Челентано",
            "два часа назад",
            getString(R.string.content20)
        )
        items.add(feed1)
        val feed2 = ModelFeed(
            19,
            "64",
            "13", 54,
            "10",
            R.drawable.prof19,
            R.drawable.content19,
            "KBTU Startup Incubator",
            "30 окт 2018",
            getString(R.string.content19)
        )
        items.add(feed2)
        val feed7 = ModelFeed(
            7,
            "101",
            "3", 24,
            "7",
            R.drawable.prof3,
            R.drawable.content3,
            "Way",
            "час назад",
            "An asphalt road through a dark forest"
        )
        items.add(feed7)

        val feed4 = ModelFeed(
            4,
            "144",
            "3", 12,
            "14",
            R.drawable.prof7,
            R.drawable.content7,
            "Идеи для бизнеса | Стартапы",
            "три часа назад",
            getString(R.string.content7)
        )
        items.add(feed4)
        val feed5 = ModelFeed(
            5,
            "120",
            "300",
            140,
            "230",
            R.drawable.prof12,
            R.drawable.first,
            "Bagzat Dias",
            "сейчас",
            "Работает!!!"
        )
        items.add(feed5)
        val feed6 = ModelFeed(
            6,
            "130",
            "3", 13,
            "2",
            R.drawable.prof2,
            R.drawable.content2,
            "Dr DW | WINDOWS | ANDROID | IOS | APPLE",
            "час назад",
            "Прежде чем лопать эти пузырики, вспомни, что воздух внутри этих пузыриков прибыл к тебе из Китая."
        )
        items.add(feed6)
        val feed13 = ModelFeed(
            13,
            "34",
            "12", 25,
            "6",
            R.drawable.prof13,
            R.drawable.content13,
            "Джон Рокфеллер и другие ",
            "15 июл 2019",
            getString(R.string.content13)
        )
        items.add(feed13)
        val feed14 = ModelFeed(
            14,
            "33",
            "12", 21,
            "5",
            R.drawable.prof14,
            R.drawable.content14,
            "overpublic1",
            "Сегодня в 2:46",
            getString(R.string.content14)
        )
        items.add(feed14)

        val feed8 = ModelFeed(
            8,
            "14",
            "1", 10,
            "1",
            R.drawable.prof8,
            R.drawable.content8,
            "TED RUS - ted talks на русском",
            "Вчера в 15:09",
            getString(R.string.content8)
        )
        items.add(feed8)
        val feed3 = ModelFeed(
            3,
            "1344",
            "23",
            212,
            "24",
            R.drawable.prof6,
            R.drawable.content6,
            "НЛП-Клуб online",
            "два часа назад",
            getString(R.string.content6)
        )
        items.add(feed3)
        val feed9 = ModelFeed(
            9,
            "1",
            "1", 1,
            "1",
            R.drawable.prof9,
            R.drawable.content9,
            "Банк бизнес идей",
            "Вчера в 17:10",
            getString(R.string.content9)
        )
        items.add(feed9)
        val feed10 = ModelFeed(
            10,
            "15",
            "1", 12,
            "2",
            R.drawable.prof10,
            R.drawable.content10,
            "DarkWeb",
            "Вчера в 00:00",
            getString(R.string.content10)
        )
        items.add(feed10)
        val feed11 = ModelFeed(
            11,
            "3",
            "1", 2,
            "1",
            R.drawable.prof11,
            R.drawable.content11,
            "ART BOX",
            "Сегодня в 2:46",
            getString(R.string.content11)
        )
        items.add(feed11)
        val feed12 = ModelFeed(
            12,
            "15",
            "2", 3,
            "1",
            R.drawable.prof12,
            R.drawable.content12,
            "Page OVERLORDS",
            "22 фев в 16:18 ",
            getString(R.string.content12)
        )
        items.add(feed12)

        val feed15 = ModelFeed(
            15,
            "46",
            "15", 26,
            "11",
            R.drawable.prof15,
            R.drawable.content15,
            "Wasted",
            "38 минут назад",
            getString(R.string.content15)
        )
        items.add(feed15)
        val feed16 = ModelFeed(
            16,
            "37",
            "13", 24,
            "17",
            R.drawable.prof16,
            R.drawable.content16,
            "Кладбище тупых диалогов",
            "15 минут назад",
            getString(R.string.content16)
        )
        items.add(feed16)
        val feed17 = ModelFeed(
            17,
            "65",
            "14", 46,
            "66",
            R.drawable.prof17,
            R.drawable.content17,
            "Academic Architecture",
            "28 минут назад",
            getString(R.string.content17)
        )
        items.add(feed17)
        val feed18 = ModelFeed(
            18,
            "70",
            "33", 48,
            "75",
            R.drawable.prof18,
            R.drawable.content18,
            "Мир Аниме - World of Anime",
            "вчера в 17:02",
            getString(R.string.content18)
        )
        items.add(feed18)

        val feed19 = ModelFeed(
            2,
            "104",
            "2", 21,
            "2",
            R.drawable.prof5,
            R.drawable.content5,
            "Александр Романов",
            "два часа назад",
            getString(R.string.content5)
        )
        items.add(feed19)

        val feed20 = ModelFeed(1,
            "100",
            "2",
            23,
            "6",
            R.drawable.prof4,
            R.drawable.content4,
            "Бизнес Тренды",
            "час назад",
            getString(R.string.content4)
        )
        items.add(feed20)
        return items
    }

    fun removeLike(modelFeed: ModelFeed?) {
        if (modelFeed != null) {
            adapterFeed.removeLike(modelFeed)
        }
    }

}