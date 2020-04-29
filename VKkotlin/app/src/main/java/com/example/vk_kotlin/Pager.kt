package com.example.vk_kotlin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class Pager(
    fm: FragmentManager?,
    private val fragmentsLIst: List<Fragment>
) :
    FragmentPagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return fragmentsLIst[position]
    }

    override fun getCount(): Int {
        return fragmentsLIst.size
    }

}