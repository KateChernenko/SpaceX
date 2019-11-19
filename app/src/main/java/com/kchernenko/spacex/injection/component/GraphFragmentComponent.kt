package com.kchernenko.spacex.injection.component

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kchernenko.spacex.injection.annotation.PerFragment
import com.kchernenko.spacex.injection.module.GraphFragmentModule
import com.kchernenko.spacex.injection.module.LaunchesListFragmentModule
import com.kchernenko.spacex.ui.fragment.GraphFragment
import com.kchernenko.spacex.ui.fragment.LaunchesListFragment
import dagger.BindsInstance
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules =[GraphFragmentModule::class])
interface GraphFragmentComponent {

    fun inject(fragment: GraphFragment)

    @Subcomponent.Builder
    interface ComponentBuilder {

        @BindsInstance
        fun setContextFragment(fragment: GraphFragment): ComponentBuilder
        @BindsInstance
        fun setInflate(inflater: LayoutInflater): ComponentBuilder

        @BindsInstance
        fun setViewGroup(container: ViewGroup?): ComponentBuilder

        fun build(): GraphFragmentComponent

    }
}