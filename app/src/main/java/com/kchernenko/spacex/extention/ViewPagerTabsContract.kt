package com.kchernenko.spacex.extention

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.kchernenko.spacex.ui.adapter.ViewPagerBaseAdapter

@BindingAdapter("setPagerAdapter")
fun ViewPager.setPagerAdapter(baseAdapter: ViewPagerBaseAdapter){
    adapter = baseAdapter
}

@BindingAdapter("setViewPager")
fun TabLayout.setViewPager(viewPager: ViewPager){
    setupWithViewPager(viewPager)
}