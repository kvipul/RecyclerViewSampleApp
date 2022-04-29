package com.example.recyclerviewsampleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.recyclerviewsampleapp.*

class MyTestAdapter4 : BaseRecyclerViewAdapter() {

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

    inner class ImageViewHolder1(val view: View) : BaseViewHolder(view) {
        private val avatar = view.findViewById<ImageView>(R.id.avatar)

        init {
            avatar.setOnClickListener {
                //
                //layoutPosition
            }
        }

        override fun bind(position: Int, item: Any) {
            val imageItem = item as ImageItem
            Glide.with(view.context).load(imageItem.url).into(avatar)

//            CoroutineScope(Dispatchers.IO).launch {
//                val url = fetchProfilePicUrl()
//                CoroutineScope(Dispatchers.Main).launch {
//                    Glide.with(view.context).load(url).into(avatar)
//                }
//            }
        }

        override fun onViewRecycled() {
            Glide.with(view.context).clear(avatar)
        }

//        @WorkerThread
//        fun fetchProfilePicUrl(): String {
//            //background work
//            return ""
//        }

    }

    inner class TextViewHolder2(val view: View) : BaseViewHolder(view) {
        private val textView = view.findViewById<TextView>(R.id.text)

        override fun bind(position: Int, item: Any) {
            val textItem = item as TextItem
            textView.text = textItem.title

            if (position % 2 == 1) {
                //add background color to red
            } else {
                //white color
            }
        }
    }
}

enum class MyTestAdapterViewType2 {
    IMAGE, TEXT
}

interface MyTestAdapterItem2 {
    fun getMyTestAdapterViewType(): MyTestAdapterViewType
}




