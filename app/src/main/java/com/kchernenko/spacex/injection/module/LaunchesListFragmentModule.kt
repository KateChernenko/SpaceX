package com.kchernenko.spacex.injection.module

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kchernenko.spacex.BR
import com.kchernenko.spacex.R
import com.kchernenko.spacex.databinding.FragmentLaunchesListBinding
import com.kchernenko.spacex.injection.annotation.PerFragment
import com.kchernenko.spacex.model.LaunchModel
import com.kchernenko.spacex.ui.adapter.BaseDataBindAdapter
import com.kchernenko.spacex.viewmodel.LaunchesListFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
class LaunchesListFragmentModule {

    @PerFragment
    @Provides
    fun provideDataBind(inflater: LayoutInflater, container: ViewGroup?,
        launchesListFragmentViewModel: LaunchesListFragmentViewModel,
        adapterLaunches: BaseDataBindAdapter<LaunchModel>): FragmentLaunchesListBinding {
        val dataBind = DataBindingUtil.inflate(inflater, R.layout.fragment_launches_list, container, false) as FragmentLaunchesListBinding
        dataBind.launchesListFragmentViewModel = launchesListFragmentViewModel
        dataBind.adapterLaunches = adapterLaunches

        return dataBind
    }

    @PerFragment
    @Provides
    fun provideAdapterList(viewModel: LaunchesListFragmentViewModel) = BaseDataBindAdapter<LaunchModel>(
        R.layout.item_data_launch, BR.dataLaunchModel, null, viewModel
    )


}