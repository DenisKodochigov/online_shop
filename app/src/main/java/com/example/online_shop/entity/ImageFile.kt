package com.example.online_shop.entity

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.FileOutputStream


object ImageFile {

    fun saveImage(context: Context, bitmap: Bitmap, fileName: String) {
        val fileOutputStream: FileOutputStream
        try {
            Log.d("KDS", "ImageFile.saveImage")
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream)
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadImageBitmap(context: Context, fileName: String): Bitmap? {
        var bitmap: Bitmap? = null
        try{
            val fileInputStream = context.openFileInput(fileName)
            bitmap = BitmapFactory.decodeStream(fileInputStream)
            fileInputStream.close()
        }catch(e:Exception) {
            e.printStackTrace()
        }
        return bitmap
    }
}