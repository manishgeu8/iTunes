package com.example.itunes.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(browsedSongsEntity: BrowsedSongsEntity): Long

    @Query("SELECT * FROM browsed_songs")
    suspend fun getHistoryList(): List<BrowsedSongsEntity>
}