package com.example.recyclerviewsampleapp

data class ImageItem(val url: String) : MyTestAdapterItem {
    override fun getMyTestAdapterViewType() = MyTestAdapterViewType.IMAGE
}

data class TextItem(val title: String) : MyTestAdapterItem {
    override fun getMyTestAdapterViewType() = MyTestAdapterViewType.TEXT
}

data class ParentList(val data: ArrayList<ImageItem>)

