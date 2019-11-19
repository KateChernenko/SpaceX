package com.kchernenko.spacex.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.kchernenko.spacex.SpaceXApp
import com.kchernenko.spacex.databinding.FragmentLaunchesListBinding
import javax.inject.Inject

class LaunchesListFragment : BaseFragment() {

    @Inject
    lateinit var mBinding: FragmentLaunchesListBinding

    companion object {
        const val TAG = "LaunchesListFragment"
        fun newInstance(): LaunchesListFragment {
            val fragment = LaunchesListFragment()
            val args = Bundle()

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        SpaceXApp.getAppComponent().getLaunchesListFragmentComponent().setContextFragment(this).setInflate(inflater)
            .setViewGroup(container).build().inject(this)
        return mBinding.root
    }

    override fun setLiveDataObservers() {
        mBinding.launchesListFragmentViewModel?.liveDataOnItemClick?.observe(this, Observer {
            (baseFragmentContract as LaunchesListFragmentContract).navigateToDetailsScreen(it)
        })
    }

    override fun removeObservers() {
//        if (!::mBinding.isInitialized)
//            mBinding.launchesListFragmentViewModel?.liveDataOnItemClick?.removeObservers(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseFragmentContract = context as LaunchesListFragmentContract
    }

    interface LaunchesListFragmentContract : BaseFragmentContract {
        fun navigateToDetailsScreen(id: Int)
    }
}