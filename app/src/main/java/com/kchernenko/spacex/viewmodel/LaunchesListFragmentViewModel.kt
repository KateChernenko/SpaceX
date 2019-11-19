package com.kchernenko.spacex.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.kchernenko.spacex.BR
import com.kchernenko.spacex.injection.annotation.PerFragment
import com.kchernenko.spacex.injection.provider.LaunchesServiceProvider
import com.kchernenko.spacex.model.LaunchModel
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@PerFragment
class LaunchesListFragmentViewModel @Inject constructor(
    private var arrayList: ArrayList<LaunchModel>,
    private val launchesServiceProvider: LaunchesServiceProvider
) : BaseViewModel() {

    private var mDisposableList: Disposable? = null
    var liveDataOnItemClick: MutableLiveData<Int> = MutableLiveData()
    val pageSize = 20
    var lastItemId = 0
    var isLastPage = false

    @Bindable
    var loadingInProgress: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loadingInProgress)
        }

    @Bindable
    var dataListLaunches: ArrayList<LaunchModel> = arrayList
        set(value) {
            field = value
            notifyPropertyChanged(BR.dataListLaunches)
        }

    init {
        if (arrayList.size == 0) {
            getLaunchesPagination()
        }

    }

    fun getLaunchesPagination() {
        if (lastItemId > 0)
            loadingInProgress = true
        mDisposableList = launchesServiceProvider.getLaunchesPagination(pageSize, lastItemId).subscribeBy(
            onSuccess = {
                if (it.size > 0) {
                    arrayList.addAll(it)
                    notifyPropertyChanged(BR.dataListLaunches)
                } else {
                    isLastPage = true
                }

                loadingInProgress = false
            }, onError = {
                loadingInProgress = false
                checkException(it)
            }
        )
    }

    fun onItemClick(itemId: Int) {
        liveDataOnItemClick.value = itemId
    }

    override fun onCleared() {
        super.onCleared()
        if (!mDisposableList?.isDisposed!!) mDisposableList?.dispose()
    }
}