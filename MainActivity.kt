package com.example.fondopantalla

import android.app.WallpaperManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    // Lista de tus 10 imágenes en drawable
    private val wallpaperList = listOf(
        R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
        R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8,
        R.drawable.img9, R.drawable.img10
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.viewPagerWallpapers)
        val btnSet = findViewById<Button>(R.id.btnSetWallpaper)

        // Configurar el adaptador y el deslizamiento fluido
        viewPager.adapter = WallpaperAdapter(wallpaperList)

        // Efecto de zoom suave al deslizar
        viewPager.setPageTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        // Acción del botón
        btnSet.setOnClickListener {
            val currentImg = wallpaperList[viewPager.currentItem]
            val wm = WallpaperManager.getInstance(this)
            try {
                wm.setResource(currentImg)
                Toast.makeText(this, "Fondo aplicado con éxito", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al aplicar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Clase del Adaptador
    class WallpaperAdapter(private val images: List<Int>) :
        RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val img: ImageView = view.findViewById(R.id.ivWallpaper)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_wallpaper, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.img.setImageResource(images[position])
        }

        override fun getItemCount() = images.size
    }
}
