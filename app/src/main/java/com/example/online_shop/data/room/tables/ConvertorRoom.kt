package com.example.online_shop.data.room.tables

import android.net.Uri
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ConvertorRoom {
    val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val uriAdapter: JsonAdapter<Uri> = moshi.adapter(Uri::class.java)
    @TypeConverter
    fun uriToJSON(source: Uri?): String = uriAdapter.toJson(source)
    @TypeConverter
    fun uriFromJSON(sourceStr: String) = uriAdapter.fromJson(sourceStr)
}