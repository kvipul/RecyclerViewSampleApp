package com.example.recyclerviewsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecyclerViewDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_demo)

        //creating dummy data, ideally data creation should be part of viewmodel or repository
        val dataList = arrayListOf<Any>()
        dataList.add(ImageItem("https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_960_720.jpg"))
        dataList.add(ImageItem("https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_960_720.jpg"))
        dataList.add(ImageItem("https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_960_720.jpg"))
        dataList.add(ImageItem("https://cdn.pixabay.com/photo/2014/11/30/14/11/cat-551554_960_720.jpg"))
        dataList.add(TextItem("RecyclerView item1"))
        dataList.add(TextItem("RecyclerView item2"))
        dataList.add(TextItem("RecyclerView item3"))
        dataList.add(TextItem("RecyclerView item4"))
        dataList.add(TextItem("RecyclerView item5"))



//        val adapter = MyTestAdapter()
        val adapter = MyTestAdapter2()
//        val adapter = MyTestAdapter3()
//        val adapter = MyTestAdapter4()

        adapter.setData(dataList)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(this@RecyclerViewDemoActivity, LinearLayoutManager.VERTICAL, false)

            //optional
            addItemDecoration(DividerItemDecoration(this@RecyclerViewDemoActivity, DividerItemDecoration.VERTICAL))
            itemAnimator = DefaultItemAnimator()
        }


        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            adapter.removeItem(0)
        }
    }
}