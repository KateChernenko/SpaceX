package com.kchernenko.spacex.viewmodel

import androidx.databinding.Bindable
import com.kchernenko.spacex.BR
import com.kchernenko.spacex.injection.annotation.PerFragment
import com.kchernenko.spacex.model.LaunchModel
import javax.inject.Inject

@PerFragment
class GraphFragmentViewModel @Inject constructor(data: ArrayList<LaunchModel>):BaseViewModel() {

    @Bindable
    var dataList: ArrayList<LaunchModel> = data
        set(value) {
            field = value
            notifyPropertyChanged(BR.dataList)
        }


}