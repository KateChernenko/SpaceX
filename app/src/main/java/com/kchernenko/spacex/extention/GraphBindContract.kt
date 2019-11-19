package com.kchernenko.spacex.extention

import androidx.databinding.BindingAdapter
import com.kchernenko.spacex.model.LaunchModel
import com.kchernenko.spacex.ui.view.GraphView
import java.util.*

@BindingAdapter("app:graphData")
fun graphData(view: GraphView, dataList: List<LaunchModel>) {
    val graphData = mutableListOf<GraphView.MonthLaunches>()
    val grouped = dataList.groupBy {
        val calendar = GregorianCalendar.getInstance()
        calendar.time = Date(it.timestamp * 1000)
        return@groupBy "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH)}"
    }

    grouped.forEach {
        graphData.add(GraphView.MonthLaunches(it.value[0].timestamp * 1000,
            it.value.size)) }
    graphData.sortBy { it.timestamp }
    view.setData(graphData)
}

