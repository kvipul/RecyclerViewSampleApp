package com.example.recyclerviewsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NestedRecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_demo)

        //creating dummy data, ideally data creation should be part of viewmodel or repository
        val dataList = arrayListOf<ParentList>()
        for (i in 1..10) {
            val temp = arrayListOf<ImageItem>()
            for (j in 1..50) {
                temp.add(ImageItem("https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_960_720.jpg"))
            }
            dataList.add(ParentList(temp))
        }

        val adapter = NestedListAdapter()
        adapter.setData(dataList)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(this@NestedRecyclerViewActivity, LinearLayoutManager.VERTICAL, false)

            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(this@NestedRecyclerViewActivity, DividerItemDecoration.VERTICAL))
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            Log.e("viewpool count", "${recyclerView.recycledViewPool.getRecycledViewCount(4)}")
        }
    }
}

//        val layouts = arrayListOf<Int>(
//            R.layout.match_parent,
//            R.layout.group,
//            R.layout.chain,
//            R.layout.guideline,
//            R.layout.barrier,
//            R.layout.circular_positioning,
//            R.layout.thumbnail,
//            R.layout.margins,
//            R.layout.relative_layout
//        )