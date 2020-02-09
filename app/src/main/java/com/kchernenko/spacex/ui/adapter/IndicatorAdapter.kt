package com.kchernenko.spacex.ui.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import com.kchernenko.spacex.R


class IndicatorAdapter(private val items:ArrayList<String>): RecyclerView.Adapter<IndicatorAdapter.MyViewHolder>() {

//    var items = ArrayList<String>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_indicator, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding?.executePendingBindings()
    }


    class MyViewHolder(v: View) :  RecyclerView.ViewHolder(v) {
        val binding: ViewDataBinding? = DataBindingUtil.bind(v)
    }
}