package com.example.online_shop.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.online_shop.data.room.tables.PersonDB

@Database(entities = [PersonDB::class,], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao
}
