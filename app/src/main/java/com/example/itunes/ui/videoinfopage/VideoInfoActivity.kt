@file:Suppress("DEPRECATION")

package com.example.itunes.ui.videoinfopage

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.itunes.R
import com.example.itunes.databinding.ActivityVideoInfoBinding
import com.example.itunes.model.ResultsItem
import com.google.gson.Gson

class VideoInfoActivity : AppCompatActivity() {

    private var _binding: ActivityVideoInfoBinding?= null
    private var resultsItem: ResultsItem?=null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Song Info"
        _binding = ActivityVideoInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val gson = Gson()
        resultsItem = gson.fromJson(intent.getStringExtra("videoInfo"), ResultsItem::class.java)
        
        resultsItem?.let { getImageGlide(binding.imgViewThumbnailVideoInfo, it.artworkUrl100) }
        binding.txtTrackName.text = resultsItem?.trackName ?: ""
        binding.txtAuthorName.text = resultsItem?.artistName ?: ""
        binding.txtArtistID.text = resultsItem?.artistId.toString()
        binding.txtReleaseDate.text = resultsItem?.releaseDate ?: ""
        binding.txtTrackPrice.text = resultsItem?.trackPrice.toString()
        binding.txtGenre.text = resultsItem?.primaryGenreName ?: ""
        binding.trTrackTime.text = (resultsItem?.trackTimeMillis?.div(60000) ?: 0.0).toString()
        binding.txtCountry.text = resultsItem?.country ?: ""
        binding.txtTrackNumber.text = resultsItem?.trackNumber.toString()
    }

    private fun getImageGlide(imgItem: ImageView, strImgPath: String) {
        try {
            val imagePath: String = strImgPath
            Glide.with(this)
                .load(imagePath)
                .placeholder(R.drawable.ic_insert_photo_24px)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(imgItem)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }
}