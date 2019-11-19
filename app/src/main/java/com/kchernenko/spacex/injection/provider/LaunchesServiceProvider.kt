package com.kchernenko.spacex.injection.provider

import com.kchernenko.spacex.api.LaunchesServiceApi
import com.kchernenko.spacex.injection.annotation.Job
import com.kchernenko.spacex.injection.annotation.Main
import com.kchernenko.spacex.model.LaunchFullModel
import com.kchernenko.spacex.model.LaunchModel
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class LaunchesServiceProvider @Inject constructor(@Job private var mJobScheduler: Scheduler,
                                                  @Main private var mUiScheduler: Scheduler,
                                                  private var serviceApi: LaunchesServiceApi){

    fun getLaunchesPagination(limit:Int, offset:Int):Single<ArrayList<LaunchModel>>{
        return serviceApi.getLaunchesPagination(limit, offset)
            .observeOn(mUiScheduler)
            .subscribeOn(mJobScheduler)
    }

    fun getLaunch(id:Int):Single<LaunchFullModel>{
        return serviceApi.getLaunch(id.toString())
            .observeOn(mUiScheduler)
            .subscribeOn(mJobScheduler)
    }
}