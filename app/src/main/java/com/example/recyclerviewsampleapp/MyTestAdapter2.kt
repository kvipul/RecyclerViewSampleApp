package com.example.recyclerviewsampleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyTestAdapter2 : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<Any>()

    fun setData(newData: ArrayList<Any>){
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    fun removeItem(index: Int) {
        list.removeAt(index)
        notifyItemRemoved(index)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ImageViewHolder1 -> holder.setImage(list[position])
            is TextViewHolder2 -> holder.setText(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        if (list[position] is ImageItem) {
            return MyTestAdapterViewType.IMAGE.ordinal
        } else {
            return MyTestAdapterViewType.TEXT.ordinal
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        if (holder is ImageViewHolder1) {
            Glide.with(holder.view.context).clear(holder.avatar)
        }
    }

    inner class ImageViewHolder1(val view: View) : RecyclerView.ViewHolder(view) {
        val avatar = view.findViewById<ImageView>(R.id.avatar)

        fun setImage(item: Any) {
            val imageItem = item as ImageItem
            Glide.with(view.context).load(imageItem.url).into(avatar)
        }
    }

    inner class TextViewHolder2(val view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.findViewById<TextView>(R.id.text)

        fun setText(item: Any){
            val textItem = item as TextItem
            textView.text = textItem.title
        }
    }

    enum class MyTestAdapterViewType {
        IMAGE, TEXT
    }
}


