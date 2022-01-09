package com.example.gallery.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gallery.R
import kotlinx.android.synthetic.main.photo_layout.view.*

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun fromParent(parent: ViewGroup) =
            PhotoViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.photo_layout, parent, false)
            )
    }

    private val photo by lazy { itemView.photo }

    fun bindView(position: Int, photosUri: List<String>) {
        loadImageByUrl(photosUri[position])
    }

    private fun loadImageByUrl(url: String) {
        Glide.with(photo.context)
            .load(url)
            .into(photo)
    }
}