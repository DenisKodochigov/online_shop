package com.example.online_shop.di

import com.example.online_shop.data.api.MoskyAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val serverApi = "https://run.mocky.io/"
        return Retrofit
            .Builder()
            .baseUrl(serverApi)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    @Provides
    fun provideApi(retrofit: Retrofit): MoskyAPI {
        return retrofit.create(MoskyAPI::class.java)
    }
}