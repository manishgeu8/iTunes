package com.example.itunes.model

import kotlinx.serialization.Serializable

@Serializable
data class ResultsItem(
    val artworkUrl30: String = "",
    val trackTimeMillis: Int = 0,
    val country: String = "",
    val previewUrl: String = "",
    val artistId: Int = 0,
    val trackName: String = "",
    val collectionName: String = "",
    val artistViewUrl: String = "",
    val discNumber: Int = 0,
    val trackCount: Int = 0,
    val artworkUrl60: String = "",
    val wrapperType: String = "",
    val currency: String = "",
    val collectionId: Int = 0,
    val trackExplicitness: String = "",
    val collectionViewUrl: String = "",
    val trackNumber: Int = 0,
    val releaseDate: String = "",
    val kind: String = "",
    val trackId: Int = 0,
    val collectionPrice: Double = 0.0,
    val discCount: Int = 0,
    val primaryGenreName: String = "",
    val trackPrice: Double = 0.0,
    val collectionExplicitness: String = "",
    val trackViewUrl: String = "",
    val artworkUrl100: String = "",
    val trackCensoredName: String = "",
    val artistName: String = "",
    val collectionCensoredName: String = "",
    val collectionArtistId: Int = 0,
    val collectionArtistViewUrl: String = "",
)