package com.miso.vyns

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.miso.vyns.adapters.AlbumItemAdapter
import com.miso.vyns.model.VynsAlbum

//@BindingAdapter("android:text")
fun bindLong(txt: TextView, valor: String?) {
    Log.d("ADAPTERS", "bindlong inicio")
    valor?.let {
        txt.text=valor.length.toString()
    }
}


@BindingAdapter("app:loadImage")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Log.d("ADAPTERS", "bindImage inicio")
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<VynsAlbum>?) {
    Log.d("ADAPTERS", "bindLista inicio")
    val adapter = recyclerView.adapter as AlbumItemAdapter
    adapter.submitList(data)
}

class BindingAdapters {
}