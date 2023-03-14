package com.example.online_shop.entity

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.online_shop.App
import com.example.online_shop.R
import com.example.online_shop.data.DataRepository

class ErrorApp {

    fun errorApi (errorMessage:String){
        val toastMessage: String
        when(errorMessage){
            "HTTP 401 " -> {
                toastMessage = App.context.getString(R.string.error401)
            }
            "HTTP 402 " -> {
                toastMessage = App.context.getString(R.string.error402)
            }
            "HTTP 404 " -> {
                toastMessage = App.context.getString(R.string.error404)
            }
            "HTTP 429 " -> {
                toastMessage = App.context.getString(R.string.error429)
            }
            else -> {
                toastMessage = App.context.getString(R.string.errorUser)
                Log.d("KDS", "Error $errorMessage")
            }

        }
        if (toastMessage.isNotEmpty()) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(App.context, toastMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

