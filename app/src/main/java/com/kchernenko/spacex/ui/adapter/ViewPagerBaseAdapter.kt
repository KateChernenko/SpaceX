package com.kchernenko.spacex.ui.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.kchernenko.spacex.ui.fragment.BaseFragment

class ViewPagerBaseAdapter(private val dataList: ArrayList<BaseFragment>, private val tabsList:List<String>, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): BaseFragment {
        return dataList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabsList[position]
    }

    override fun getCount(): Int {
        return dataList.size
    }
}