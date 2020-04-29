package com.example.vk_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), AdapterFeed.FragmentLikeListener, AdapterFeed.FragmentButtonListener, AdapterLike.FragmentLikeListener {
    var fragmentFeed: Fragment = FragmentFeed()
    var fragmentLike: Fragment = FragmentLike()
    private lateinit var pager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter
    var list: MutableList<Fragment> =  ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.add(fragmentFeed)
        list.add(fragmentLike)
        pager = findViewById(R.id.pager)
        pagerAdapter = Pager(supportFragmentManager, list)
        pager.adapter = pagerAdapter
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener)

    }
     private val navigationListener =
         BottomNavigationView.OnNavigationItemSelectedListener {  item ->
             when (item.itemId) {
                 R.id.feed -> {
                     pager.setCurrentItem(0, false)
                     supportActionBar!!.title = "Новости"
                     return@OnNavigationItemSelectedListener true

                 }
                 R.id.like -> {
                     pager.setCurrentItem(1, false)
                     supportActionBar!!.title = "Закладки"
                     return@OnNavigationItemSelectedListener true
                 }
             }
             return@OnNavigationItemSelectedListener false

}

    override fun removeItemLike(feed: ModelFeed?) {
        (fragmentFeed as FragmentFeed).removeLike(feed)
        (fragmentLike as FragmentLike).removeLike(feed)
    }

    override fun myClick(modelFeed: ModelFeed?, option: Int) {
       val fragment = supportFragmentManager.findFragmentById(R.id.pager)
        if(option == 1) modelFeed?.let { (fragment as FragmentLike?)?.saveNews(it) }
        else (fragment as FragmentLike?)?.removeNews(modelFeed)
            }
}
