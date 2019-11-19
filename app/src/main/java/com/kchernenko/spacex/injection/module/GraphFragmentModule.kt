package com.kchernenko.spacex.injection.module

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kchernenko.spacex.R
import com.kchernenko.spacex.databinding.FragmentGraphBinding
import com.kchernenko.spacex.injection.annotation.PerFragment
import com.kchernenko.spacex.viewmodel.GraphFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
class GraphFragmentModule {

    @PerFragment
    @Provides
    fun provideDataBind(inflater: LayoutInflater, container: ViewGroup?,
                        graphFragmentViewModel: GraphFragmentViewModel): FragmentGraphBinding {
        val dataBind = DataBindingUtil.inflate(inflater, R.layout.fragment_graph, container, false) as FragmentGraphBinding
        dataBind.graphFragmentViewModel = graphFragmentViewModel
        return dataBind
    }
}