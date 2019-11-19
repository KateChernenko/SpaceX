package com.kchernenko.spacex.viewmodel

import androidx.databinding.Bindable
import com.kchernenko.spacex.BR
import com.kchernenko.spacex.R
import com.kchernenko.spacex.SpaceXApp
import com.kchernenko.spacex.injection.provider.LaunchesServiceProvider
import com.kchernenko.spacex.model.LaunchFullModel
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class DetailsFragmentViewModel @Inject constructor(
    private var launchFullModel: LaunchFullModel,
    private val launchId: Int,
    private val launchesServiceProvider: LaunchesServiceProvider
) : BaseViewModel() {

    private var mDisposableDetails: Disposable? = null

    @Bindable
    var loadingInProgress: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loadingInProgress)
        }

    @Bindable
    var title: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @Bindable
    var imagePath: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.imagePath)
        }

    @Bindable
    var titleDetails: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.titleDetails)
        }
    @Bindable
    var siteName: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.siteName)
        }

    @Bindable
    var rocket: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.rocket)
        }

    init {
        if (launchFullModel.name.isEmpty()){
            getLaunchInfo()
        } else {
            updateViews()
        }
    }

    private fun getLaunchInfo() {
        loadingInProgress = true
        mDisposableDetails = launchesServiceProvider.getLaunch(launchId).subscribeBy(
            onSuccess = {
                launchFullModel = it
                updateViews()
                loadingInProgress = false
            }, onError = {
                checkException(it)
            }
        )
    }

    private fun updateViews() {
        title = launchFullModel.name
        if (launchFullModel.links != null)
            imagePath = launchFullModel.links?.missionPatch!!
        titleDetails = launchFullModel.details
        if (launchFullModel.launchSite != null) {
            val text = launchFullModel.launchSite?.siteName + ", " + launchFullModel.launchSite?.siteNameLong
            siteName = SpaceXApp.getInstance().getString(R.string.site_name, text)
        }
        if (launchFullModel.rocket != null) {
            rocket = SpaceXApp.getInstance().getString(R.string.rocket_name, launchFullModel.rocket?.rocketName)
        }


    }
}