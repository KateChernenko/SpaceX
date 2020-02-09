package com.kchernenko.spacex.extention

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kchernenko.spacex.R
import com.kchernenko.spacex.SpaceXApp
import com.kchernenko.spacex.model.LaunchModel
import com.kchernenko.spacex.ui.adapter.BaseDataBindAdapter
import com.kchernenko.spacex.ui.adapter.IndicatorAdapter
import com.kchernenko.spacex.ui.view.DividerDecoration
import com.kchernenko.spacex.viewmodel.LaunchesListFragmentViewModel



@BindingAdapter("setDataListLinear", "setLinearAdapter")
fun RecyclerView.setDataListLinear(dataList: List<LaunchModel>?, dataBindAdapter: BaseDataBindAdapter<LaunchModel>?) {
    if (adapter == null) {
        layoutManager = LinearLayoutManager(context)
        adapter = dataBindAdapter
    }
    if (dataList != null)
        dataBindAdapter?.dataList = dataList
}

@BindingAdapter("setIndicatorAdapter")
fun RecyclerView.setIndicatorAdapter(dataBindAdapter: IndicatorAdapter) {
    if (adapter == null) {
        adapter = dataBindAdapter
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        layoutManager = LinearLayoutManager(context)
        val count = adapter!!.itemCount
        post {
            val space = ((width - (SpaceXApp.getInstance().resources.getDimension(R.dimen.activity_horizontal_margin)*5)) / count).toInt()
            addItemDecoration(DividerDecoration(space), LinearLayoutManager.HORIZONTAL)
        }

    }
}

@BindingAdapter("setPaginationListener")
fun RecyclerView.setPaginationListener(launchesListFragmentViewModel: LaunchesListFragmentViewModel) {
    addOnScrollListener(object: RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItemCount = layoutManager?.childCount!!
            val totalItemCount = layoutManager?.itemCount!!
            val firstVisibleItemPosition = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

            if (!launchesListFragmentViewModel.loadingInProgress && !launchesListFragmentViewModel.isLastPage){
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= launchesListFragmentViewModel.pageSize) {
                    launchesListFragmentViewModel.lastItemId = launchesListFragmentViewModel.dataListLaunches.size
                    launchesListFragmentViewModel.getLaunchesPagination()
                }
            }

        }
    })
}

@BindingAdapter("setImageGlide")
fun ImageView.setImageGlide(path:String?){
    if (path != null)
    Glide.with(context).load(path).into(this)
}