package com.example.fondopantalla

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class WallpaperAdapter(private val images: List<Int>) :
    RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {

    class WallpaperViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ivWallpaper)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wallpaper, parent, false)
        return WallpaperViewHolder(view)
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        holder.imageView.setImageResource(images[position])
    }

    override fun getItemCount(): Int = images.size
}