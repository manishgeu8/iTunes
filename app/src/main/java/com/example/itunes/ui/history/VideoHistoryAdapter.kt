package com.example.itunes.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.itunes.R
import com.example.itunes.databinding.VideoAdapterBinding
import com.example.itunes.model.ResultsItem
import com.example.itunes.room.BrowsedSongsEntity


class VideoHistoryAdapter(
    private var c: Context,
    private var videos: List<BrowsedSongsEntity>,
) : RecyclerView.Adapter<VideoHistoryAdapter.ViewHolder>() {

        //VIEWHOLDER IS INITIALIZED
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = VideoAdapterBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        //DATA IS BOUND TO VIEWS
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val videos = videos[position]

            with(holder.binding) {
                videos.thumbnail_url?.let { getImageGlide(imgViewThumbnail, it) }
                txtSongName.text = videos.title
            }
        }

        override fun getItemCount(): Int {
            return videos.size
        }

        /**
         * View holder class
         */
        class ViewHolder(val binding: VideoAdapterBinding) : RecyclerView.ViewHolder(binding.root)

        private fun getImageGlide(imgItem: ImageView, strImgPath: String) {
            try {
                val Image_Path: String = strImgPath
                Glide.with(c)
                    .load(Image_Path)
                    .placeholder(R.drawable.ic_insert_photo_24px)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .into(imgItem)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
}