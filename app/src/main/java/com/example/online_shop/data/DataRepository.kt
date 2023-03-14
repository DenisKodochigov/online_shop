package com.example.online_shop.data

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.example.online_shop.data.api.DataSourceAPI
import com.example.online_shop.data.api.FlashDTO
import com.example.online_shop.data.api.LatestDTO
import com.example.online_shop.data.room.DataSourceDB
import com.example.online_shop.entity.ImageFile
import com.example.online_shop.entity.Person
import javax.inject.Inject


class DataRepository @Inject constructor() {

    private val dataSourceAPI = DataSourceAPI()

    private val dataSourceDB = DataSourceDB()

    fun checkPerson(firstName:String, lastName:String, password: String, email: String): Boolean {
        val presencePerson = dataSourceDB.checkPerson(firstName, lastName, password)
        if (! presencePerson) dataSourceDB.addPerson(firstName, lastName, password, email)
        return presencePerson
    }

    fun loginPerson(person: Person): Person {
        val result = dataSourceDB.loginPerson(person)
        Log.d("KDS", "DataRepository.loginPersonP $result")
        return result
    }
    fun savePerson(person: Person) {
        Log.d("KDS", "DataRepository.savePerson person = $person")
        dataSourceDB.savePerson(person)
    }
    suspend fun getLatest(person: Person): LatestDTO? {
        val latestDTO = dataSourceAPI.getLatest(person)
        Log.d("KDS", "DataRepository.getLatest latestDTO = $latestDTO")
        return latestDTO
    }
    suspend fun getFlash(): FlashDTO? {
        val flashDTO = dataSourceAPI.getFlash()
        Log.d("KDS", "DataRepository.getFlash flashDTO = $flashDTO")
        return flashDTO
    }
    suspend fun getBrands(): LatestDTO? {
        val latestDTO = dataSourceAPI.getBrands()
        Log.d("KDS", "DataRepository.getLatest latestDTO = $latestDTO")
        return latestDTO
    }

    fun saveImage(context: Context, imageBitmap: Bitmap, fileName: String){
        ImageFile.saveImage(context, imageBitmap, fileName)
    }
}
//################################################################################################
