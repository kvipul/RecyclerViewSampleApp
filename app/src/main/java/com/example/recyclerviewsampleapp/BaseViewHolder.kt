package com.example.recyclerviewsampleapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(position: Int, item: Any)
}







































//=====================================================================================================
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(position: Int, item: Any)

    open fun onViewRecycled() {

    }
}

abstract class BaseRecyclerViewAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    override fun onViewRecycled(holder: BaseViewHolder) {
        super.onViewRecycled(holder)
        holder.onViewRecycled()
    }
}