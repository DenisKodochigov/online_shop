package com.example.online_shop.di

import android.content.Context
import com.example.online_shop.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@InstallIn(SingletonComponent::class)
//@Module
//object ContextModule {
//    @Provides
//    @Singleton
//    fun provideContext(@ApplicationContext context: Context): Context {
//        return context.applicationContext
//    }
//}
