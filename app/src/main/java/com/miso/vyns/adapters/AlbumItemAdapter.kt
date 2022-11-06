package com.miso.vyns.adapters

import com.miso.vyns.R
import com.miso.vyns.model.VynsAlbum
import com.miso.vyns.databinding.AlbumItemBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


class AlbumItemAdapter : ListAdapter<VynsAlbum, AlbumItemAdapter.ItemViewHolder>(AlbumItemAdapter) {

    class ItemViewHolder(private var binding: AlbumItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(vynsAlbum: VynsAlbum) {
            binding.vynsAlbum = vynsAlbum
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<VynsAlbum>() {
        override fun areItemsTheSame(oldItem: VynsAlbum, newItem: VynsAlbum): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: VynsAlbum, newItem: VynsAlbum): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        val adapterLayout = AlbumItemAdapter.ItemViewHolder(AlbumItemBinding.inflate(LayoutInflater.from(parent.context)))
        return adapterLayout

    }

    //override fun getItemCount(): Int {
    //    return dataset.size
    //}

    override fun onBindViewHolder(holder: AlbumItemAdapter.ItemViewHolder, position: Int) {
        //val item = dataset[position]
        val item= getItem(position)
        holder.bind(item)
        //holder.textViewId.text =  item.id.toString()
        //holder.textViewTitulo.text =  item.name
        //holder.textViewCover.text =  item.cover
        //holder.textViewDesc.text =  item.description
        //holder.textViewGenre.text =  item.genre
        //holder.textViewRC.text =  item.recordLabel
        //holder.textViewDate.text =  item.releaseDate
    }
}

