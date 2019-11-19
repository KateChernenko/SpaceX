package com.kchernenko.spacex.injection.component

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kchernenko.spacex.injection.annotation.PerFragment
import com.kchernenko.spacex.injection.module.PagerTabsFragmentModule
import com.kchernenko.spacex.ui.fragment.PagerTabsFragment
import dagger.BindsInstance
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules =[PagerTabsFragmentModule::class])
interface PagerTabsFragmentComponent {

    fun inject(fragment: PagerTabsFragment)

    @Subcomponent.Builder
    interface ComponentBuilder {

        @BindsInstance
        fun setContextFragment(fragment: PagerTabsFragment): ComponentBuilder
        @BindsInstance
        fun setInflate(inflater: LayoutInflater): ComponentBuilder

        @BindsInstance
        fun setViewGroup(container: ViewGroup?): ComponentBuilder

        fun build(): PagerTabsFragmentComponent

    }
}