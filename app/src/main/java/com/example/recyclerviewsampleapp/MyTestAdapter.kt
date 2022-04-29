package com.example.recyclerviewsampleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyTestAdapter : RecyclerView.Adapter<MyTestAdapter.ImageViewHolder>() {
    val TAG = MyTestAdapter::class.java.simpleName

    private val list = ArrayList<ImageItem>()

    fun setData(newData: ArrayList<ImageItem>){
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout1, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.setImage(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ImageViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val avatar = view.findViewById<ImageView>(R.id.avatar)

        fun setImage(imageItem: ImageItem) {
            Glide.with(view.context).load(imageItem.url).into(avatar)
        }
    }
}
