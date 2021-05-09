package com.example.itunes.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BrowsedSongsEntity::class], version = 1)
abstract class HistoryDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object{
        const val DATABASE_NAME: String = "browsed_songs_db"
    }
}