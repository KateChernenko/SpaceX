package com.kchernenko.spacex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.kchernenko.spacex.BR


open class BaseDataBindAdapter <T>(@LayoutRes private var layoutId: Int,
                                    private var variableId: Int,
                                    @Nullable dataList: List<T>?,
                                    private var viewModel: ViewModel?):
    RecyclerView.Adapter<BaseDataBindAdapter.BindingHolder>() {

    var dataList: List<T> = dataList ?: mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val v = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return BindingHolder(v)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.binding?.setVariable(variableId, getItem(position))
         holder.binding?.setVariable(BR.viewModel, viewModel)
        holder.binding?.executePendingBindings()
    }

    override fun getItemId(position: Int): Long = position.toLong()

    open fun getItem(position: Int): T = dataList[position]

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class BindingHolder(v: View) :  RecyclerView.ViewHolder(v) {
        val binding: ViewDataBinding? = DataBindingUtil.bind(v)
    }
}