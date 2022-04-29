package com.example.recyclerviewsampleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyTestAdapter3 : RecyclerView.Adapter<BaseViewHolder>() {

    private val list = ArrayList<MyTestAdapterItem>()

    fun setData(newData: ArrayList<MyTestAdapterItem>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    fun removeItem(index: Int) {
        list.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        if (viewType == MyTestAdapterViewType.IMAGE.ordinal) {
            return ImageViewHolder1(
                LayoutInflater.from(parent.context).inflate(R.layout.layout1, parent, false)
            )
        } else {
            return TextViewHolder2(
                LayoutInflater.from(parent.context).inflate(R.layout.layout2, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(position, list[position])
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return list[position].getMyTestAdapterViewType().ordinal
    }

    override fun onViewRecycled(holder: BaseViewHolder) {
        super.onViewRecycled(holder)
        if (holder is ImageViewHolder1) {
            Glide.with(holder.view.context).clear(holder.avatar)
        }
    }

    inner class ImageViewHolder1(val view: View) : BaseViewHolder(view) {
        val avatar = view.findViewById<ImageView>(R.id.avatar)

        override fun bind(position: Int, item: Any) {
            val imageItem = item as ImageItem
            Glide.with(view.context).load(imageItem.url).into(avatar)
        }
    }

    inner class TextViewHolder2(val view: View) : BaseViewHolder(view) {
        private val textView = view.findViewById<TextView>(R.id.text)

        override fun bind(position: Int, item: Any) {
            val textItem = item as TextItem
            textView.text = textItem.title
        }
    }
}

enum class MyTestAdapterViewType {
    IMAGE, TEXT
}

interface MyTestAdapterItem {
    fun getMyTestAdapterViewType(): MyTestAdapterViewType
}




