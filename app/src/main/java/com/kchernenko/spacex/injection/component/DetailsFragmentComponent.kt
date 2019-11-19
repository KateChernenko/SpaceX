package com.kchernenko.spacex.injection.component

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kchernenko.spacex.injection.annotation.PerFragment
import com.kchernenko.spacex.injection.module.DetailsFragmentModule
import com.kchernenko.spacex.ui.fragment.DetailsFragment
import dagger.BindsInstance
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules =[DetailsFragmentModule::class])
interface DetailsFragmentComponent {

    fun inject(fragment: DetailsFragment)

    @Subcomponent.Builder
    interface ComponentBuilder {

        @BindsInstance
        fun setContextFragment(fragment: DetailsFragment): ComponentBuilder
        @BindsInstance
        fun setInflate(inflater: LayoutInflater): ComponentBuilder

        @BindsInstance
        fun setViewGroup(container: ViewGroup?): ComponentBuilder

        fun build(): DetailsFragmentComponent

    }
}