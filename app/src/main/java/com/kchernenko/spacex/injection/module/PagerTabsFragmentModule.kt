package com.kchernenko.spacex.injection.module

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kchernenko.spacex.R
import com.kchernenko.spacex.SpaceXApp
import com.kchernenko.spacex.databinding.FragmentPagerTabsBinding
import com.kchernenko.spacex.injection.annotation.PerFragment
import com.kchernenko.spacex.ui.adapter.IndicatorAdapter
import com.kchernenko.spacex.ui.adapter.ViewPagerBaseAdapter
import com.kchernenko.spacex.ui.fragment.BaseFragment
import com.kchernenko.spacex.ui.fragment.GraphFragment
import com.kchernenko.spacex.ui.fragment.LaunchesListFragment
import com.kchernenko.spacex.ui.fragment.PagerTabsFragment
import dagger.Module
import dagger.Provides

@Module
class PagerTabsFragmentModule {

    @PerFragment
    @Provides
    fun provideDataBind(inflater: LayoutInflater, container: ViewGroup?,
                        viewPagerBaseAdapter : ViewPagerBaseAdapter,
                        adapter:IndicatorAdapter): FragmentPagerTabsBinding {
        val dataBind = DataBindingUtil.inflate(inflater, R.layout.fragment_pager_tabs, container, false) as FragmentPagerTabsBinding
        dataBind.viewPagerBaseAdapter = viewPagerBaseAdapter
        dataBind.adapter = adapter
        return dataBind
    }

    @PerFragment
    @Provides
    fun provideFragmentsList() : ArrayList<BaseFragment>{
        return arrayListOf(LaunchesListFragment.newInstance(), GraphFragment.newInstance())
    }

    @PerFragment
    @Provides
    fun provideTabsList() : List<String>{
        return arrayListOf(SpaceXApp.getInstance().getString(R.string.tab_text_1),
            SpaceXApp.getInstance().getString(R.string.tab_text_2))
    }

    @PerFragment
    @Provides
    fun provideViewPagerAdapter(fragment: PagerTabsFragment, list: ArrayList<BaseFragment>, listTabs:List<String>) =
        ViewPagerBaseAdapter(list, listTabs, fragment.childFragmentManager)

    @PerFragment
    @Provides
    fun provideAdapter() =
        IndicatorAdapter(arrayListOf("", "", "", "", ""))
}