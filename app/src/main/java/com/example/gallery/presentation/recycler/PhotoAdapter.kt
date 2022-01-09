package com.example.gallery.presentation.recycler

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.presentation.PhotoUri

class PhotoAdapter(
    private val context: Context
) : RecyclerView.Adapter<PhotoViewHolder>(),
    PhotoUri {

    private var items: List<String> = getPhotoUri()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.fromParent(parent)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindView(position, items)
    }

    override fun getItemCount() = items.size

    override fun getPhotoUri(): List<String> {
        val photosList: MutableList<String> = mutableListOf()
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        var absolutePathOfImage: String
        val projection = arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )
        val cursor: Cursor? =
            context.contentResolver.query(
                uri,
                projection,
                null,
                null,
                null
            )
        val columnIndexData: Int? = cursor?.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        while (cursor?.moveToNext() == true) {
            absolutePathOfImage = cursor.getString(columnIndexData!!)
            photosList.add(absolutePathOfImage)
        }
        return photosList
    }
}
