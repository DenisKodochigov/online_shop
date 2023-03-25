package com.example.online_shop.entity

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.online_shop.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorApp @Inject constructor(@ApplicationContext val cont: Context)  {

//    @Inject private var cont: Context

    fun errorApi (errorMessage:String){
        val toastMessage: String
        when(errorMessage){
            "HTTP 401 " -> {
                toastMessage = cont.getString(R.string.error401)
            }
            "HTTP 402 " -> {
                toastMessage = cont.getString(R.string.error402)
            }
            "HTTP 404 " -> {
                toastMessage = cont.getString(R.string.error404)
            }
            "HTTP 429 " -> {
                toastMessage = cont.getString(R.string.error429)
            }
            else -> {
                toastMessage = cont.getString(R.string.errorUser)
                Log.d("KDS", "Error $errorMessage")
            }

        }
        if (toastMessage.isNotEmpty()) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(cont, toastMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

