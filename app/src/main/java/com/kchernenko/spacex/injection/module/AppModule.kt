package com.kchernenko.spacex.injection.module

import com.kchernenko.spacex.api.LaunchesServiceApi
import com.kchernenko.spacex.injection.annotation.Job
import com.kchernenko.spacex.injection.annotation.Main
import com.kchernenko.spacex.injection.annotation.PerFragment
import com.kchernenko.spacex.injection.provider.RetrofitServiceProvider
import com.kchernenko.spacex.model.LaunchFullModel
import com.kchernenko.spacex.model.LaunchModel
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Job
    fun provideJobScheduler() = Schedulers.io()

    @Provides
    @Main
    fun provideMainScheduler() = AndroidSchedulers.mainThread()

    @Singleton
    @Provides
    fun provideRetrofit() = RetrofitServiceProvider("https://api.spacexdata.com/v3/")

    @Singleton
    @Provides
    fun provideListLaunches() = arrayListOf<LaunchModel>()

    @Provides
    fun provideLaunchFullModel() = LaunchFullModel()

    @Provides
    fun provideLaunchesServiceApi(retrofitServiceProvider: RetrofitServiceProvider) =
        retrofitServiceProvider.getServiceRetrofit(LaunchesServiceApi::class.java)

}