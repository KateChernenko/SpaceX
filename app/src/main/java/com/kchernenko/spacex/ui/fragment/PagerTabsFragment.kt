package com.kchernenko.spacex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kchernenko.spacex.SpaceXApp
import com.kchernenko.spacex.databinding.FragmentPagerTabsBinding
import javax.inject.Inject

class PagerTabsFragment:BaseFragment() {

    @Inject
    lateinit var mBinding: FragmentPagerTabsBinding

    companion object {
        const val TAG = "PagerTabsFragment"
        fun newInstance(): PagerTabsFragment {
            val fragment = PagerTabsFragment()
            val args = Bundle()

            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        SpaceXApp.getAppComponent().getPagerTabsFragmentComponent().setContextFragment(this).setInflate(inflater).setViewGroup(container).build().inject(this)
        return mBinding.root
    }

    override fun setLiveDataObservers() {

    }

    override fun removeObservers() {
    }
}