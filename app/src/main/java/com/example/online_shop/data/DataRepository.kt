package com.example.online_shop.data

import android.util.Log
import com.example.online_shop.data.api.*
import com.example.online_shop.data.api.dto.FlashDTO
import com.example.online_shop.data.api.dto.LatestDTO
import com.example.online_shop.data.room.DataSourceDB
import com.example.online_shop.entity.Person
import javax.inject.Inject

class DataRepository @Inject constructor(private var dataSourceDB: DataSourceDB,
                                         private var dataSourceAPI: DataSourceAPI) {

    fun checkPerson(person: Person): Boolean {
        val presencePerson = dataSourceDB.checkPerson(person)
        if (! presencePerson) dataSourceDB.addPerson(person)
        return presencePerson
    }

    fun loginPerson(person: Person): Person {
        val result = dataSourceDB.loginPerson(person)
        Log.d("KDS", "DataRepository.loginPersonP $result")
        return result
    }
    fun savePerson(person: Person) {
        dataSourceDB.savePerson(person)
    }
    suspend fun getLatest(person: Person): LatestDTO? {
        val latestDTO = dataSourceAPI.getLatest(person)
        Log.d("KDS", "DataRepository.getLatest latestDTO = $latestDTO")
        return latestDTO
    }
    suspend fun getFlash(): FlashDTO {
        val flashDTO = dataSourceAPI.getFlash()
        Log.d("KDS", "DataRepository.getFlash flashDTO = $flashDTO")
        return flashDTO
    }
    suspend fun getBrands(): LatestDTO? {
        val latestDTO = dataSourceAPI.getBrands()
        Log.d("KDS", "DataRepository.getLatest latestDTO = $latestDTO")
        return latestDTO
    }
}
//################################################################################################
