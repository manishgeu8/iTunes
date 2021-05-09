package com.example.itunes.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "browsed_songs")
data class BrowsedSongsEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int? = null,

    @ColumnInfo(name = "title")
    var title : String? = null,

    @ColumnInfo(name = "thumbnail_url")
    var thumbnail_url : String? = null
)