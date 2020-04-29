package com.example.vk_kotlin

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NoSwipeableViewPager : ViewPager {
    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!,
        attrs
    )


    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        // stop swipe
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // stop switching pages
        return false
    }
}