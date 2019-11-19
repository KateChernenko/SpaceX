package com.kchernenko.spacex.injection.component

import com.kchernenko.spacex.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun getPagerTabsFragmentComponent():PagerTabsFragmentComponent.ComponentBuilder
    fun getLaunchesListFragmentComponent():LaunchesListFragmentComponent.ComponentBuilder
    fun getGraphFragmentComponent():GraphFragmentComponent.ComponentBuilder
    fun getDetailsFragmentComponent():DetailsFragmentComponent.ComponentBuilder
}