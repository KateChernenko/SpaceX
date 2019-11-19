package com.kchernenko.spacex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kchernenko.spacex.BR
import com.kchernenko.spacex.SpaceXApp
import com.kchernenko.spacex.databinding.FragmentGraphBinding
import javax.inject.Inject

class GraphFragment:BaseFragment() {

    @Inject
    lateinit var mBinding: FragmentGraphBinding

    companion object {
        const val TAG = "GraphFragment"
        fun newInstance(): GraphFragment {
            val fragment = GraphFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        SpaceXApp.getAppComponent()
            .getGraphFragmentComponent()
            .setContextFragment(this)
            .setInflate(inflater)
            .setViewGroup(container)
            .build()
            .inject(this)



        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        mBinding.graphFragmentViewModel?.notifyPropertyChanged(BR.dataList)
    }

    override fun setLiveDataObservers() {

    }

    override fun removeObservers() {
    }
}