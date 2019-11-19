package com.kchernenko.spacex.api

import com.kchernenko.spacex.model.LaunchFullModel
import com.kchernenko.spacex.model.LaunchModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LaunchesServiceApi {

    @GET("launches")
    fun getLaunchesPagination(@Query("limit") limit:Int,
                              @Query("offset") offset:Int) : Single<ArrayList<LaunchModel>>

    @GET("launches/{id}")
    fun getLaunch(@Path("id") id:String) : Single<LaunchFullModel>
}