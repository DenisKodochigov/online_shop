package com.example.online_shop

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.online_shop.entity.Person
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
        var person =  Person()
    }
}