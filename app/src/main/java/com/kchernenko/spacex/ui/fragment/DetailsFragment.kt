package com.kchernenko.spacex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kchernenko.spacex.SpaceXApp
import com.kchernenko.spacex.databinding.FragmentDetailsBinding
import com.kchernenko.spacex.util.ArgumentsConstants.Companion.LAUNCH_ID
import javax.inject.Inject

class DetailsFragment:BaseFragment() {

    @Inject
    lateinit var mBinding: FragmentDetailsBinding

    companion object {
        const val TAG = "DetailsFragment"
        fun newInstance(id:Int): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putInt(LAUNCH_ID, id)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        SpaceXApp.getAppComponent().getDetailsFragmentComponent().setContextFragment(this).setInflate(inflater).setViewGroup(container).build().inject(this)
        return mBinding.root
    }

    override fun setLiveDataObservers() {

    }

    override fun removeObservers() {
    }
}