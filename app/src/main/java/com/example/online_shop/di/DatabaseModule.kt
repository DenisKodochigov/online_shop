package com.example.online_shop.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.online_shop.data.room.AppDatabase
import com.example.online_shop.data.room.DataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    lateinit var database: AppDatabase
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {

//        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
//            .build()
            database = Room.databaseBuilder( appContext, AppDatabase::class.java, "data.db")
                .build()
        return database
    }

    @Provides
    fun provideDataDao(database: AppDatabase): DataDao {
        return database.dataDao()
    }
}

