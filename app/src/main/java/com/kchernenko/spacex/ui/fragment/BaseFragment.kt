package com.kchernenko.spacex.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kchernenko.spacex.extention.dialogErrorSomethingWrong
import com.kchernenko.spacex.viewmodel.BaseViewModel

abstract class BaseFragment:Fragment() {

    open var baseViewModel:BaseViewModel = BaseViewModel()
    open var baseFragmentContract:BaseFragmentContract? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBaseObservers()
        setLiveDataObservers()
    }

    private fun setBaseObservers() {
        baseViewModel.liveDataError.observe(this, Observer { dialogErrorSomethingWrong(it) })
    }

    abstract fun setLiveDataObservers()
    abstract fun removeObservers()

    override fun onDestroy() {
        super.onDestroy()
        baseViewModel.liveDataError.removeObservers(this)
        removeObservers()
    }

    override fun onDetach() {
        super.onDetach()
        baseFragmentContract = null
    }

    interface BaseFragmentContract
}