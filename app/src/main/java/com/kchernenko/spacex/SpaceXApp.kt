package com.kchernenko.spacex

import android.app.Application
import android.content.Context
import com.kchernenko.spacex.injection.component.AppComponent
import com.kchernenko.spacex.injection.component.DaggerAppComponent

class SpaceXApp:Application() {

    companion object {
        private var instance: SpaceXApp? = null
        private lateinit var appComponent: AppComponent

        fun getInstance(): Context {
            return instance!!.applicationContext
        }
        fun getAppComponent(): AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().build()
    }
}