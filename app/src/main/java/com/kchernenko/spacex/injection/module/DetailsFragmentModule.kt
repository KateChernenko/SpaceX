package com.kchernenko.spacex.injection.module

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.kchernenko.spacex.R
import com.kchernenko.spacex.databinding.FragmentDetailsBinding
import com.kchernenko.spacex.injection.annotation.PerFragment
import com.kchernenko.spacex.ui.fragment.DetailsFragment
import com.kchernenko.spacex.util.ArgumentsConstants.Companion.LAUNCH_ID
import com.kchernenko.spacex.viewmodel.DetailsFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
class DetailsFragmentModule {

    @PerFragment
    @Provides
    fun provideLaunchId(fragment:DetailsFragment) = fragment.arguments?.getInt(LAUNCH_ID)!!

    @PerFragment
    @Provides
    fun provideDataBind(inflater: LayoutInflater, container: ViewGroup?,
                        detailsFragmentViewModel: DetailsFragmentViewModel): FragmentDetailsBinding {
        val dataBind = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false) as FragmentDetailsBinding
        dataBind.detailsFragmentViewModel = detailsFragmentViewModel
        return dataBind
    }
}