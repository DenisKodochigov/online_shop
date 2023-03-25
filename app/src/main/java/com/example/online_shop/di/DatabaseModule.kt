package com.example.online_shop.di

import android.content.Context
import androidx.room.Room
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

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {

        return Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java).build()
//        database = Room.databaseBuilder( appContext, AppDatabase::class.java, "data.db").build()

    }

    @Provides
    fun provideDataDao(database: AppDatabase): DataDao {
        return database.dataDao()
    }

}

