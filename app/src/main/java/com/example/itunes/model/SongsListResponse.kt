package com.example.itunes.model

import kotlinx.serialization.Serializable

@Serializable
data class SongsListResponse(
    val resultCount: Int = 0,
    val results: List<ResultsItem>?,
)