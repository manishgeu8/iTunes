package com.example.itunes.network

import com.example.itunes.model.SongsListResponse
import retrofit2.http.GET

interface BaseAPIService {
    companion object {
        const val searchEndPoint = "/search?term=Michael+jackson&media=musicVideo"
    }

    @GET(searchEndPoint)
    suspend fun getSongsList(): SongsListResponse
}