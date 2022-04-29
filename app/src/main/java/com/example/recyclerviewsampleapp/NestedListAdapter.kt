package com.example.recyclerviewsampleapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NestedListAdapter : RecyclerView.Adapter<NestedListAdapter.NestedListViewHolder>() {
    val TAG = NestedListAdapter::class.java.simpleName
    val viewPool = RecyclerView.RecycledViewPool()

    private val list = ArrayList<ParentList>()

    fun setData(newData: ArrayList<ParentList>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedListViewHolder {
        return NestedListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout3, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NestedListViewHolder, position: Int) {
        holder.bind(position, list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class NestedListViewHolder(val view: View) : BaseViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.title)
        private val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        init {
            recyclerView.setRecycledViewPool(viewPool)
        }

        override fun bind(position: Int, item: Any) {
            Log.e(TAG, "bind nested position=$position")

            val nestedList = item as ParentList
            title.text = "Position-" + position

            recyclerView.apply {
                val adapter = MyTestAdapter()
                adapter.setData(nestedList.data)
                setAdapter(adapter)
                val gridlm = GridLayoutManager(view.context, 2, GridLayoutManager.HORIZONTAL, false)
                gridlm.initialPrefetchItemCount = 10
                layoutManager = gridlm
            }
        }
    }
}
