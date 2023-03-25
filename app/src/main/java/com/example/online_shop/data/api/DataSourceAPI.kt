package com.example.online_shop.data.api

import com.example.online_shop.data.api.dto.FlashDTO
import com.example.online_shop.data.api.dto.LatestDTO
import com.example.online_shop.entity.Person
import com.example.online_shop.entity.Plug
import javax.inject.Inject

class DataSourceAPI @Inject constructor(private val retrofitApi: MoskyAPI) {

    suspend fun getLatest(person: Person): LatestDTO? {
//        val latestDTO = person.firstName?.let { person.password?.let { it1 ->
//            retrofitApi.getLatest(it, it1)
//        } }
//        return latestDTO
        return Plug.latest()
    }
    suspend fun getBrands(): LatestDTO? {
        return retrofitApi.getBrands()
        return Plug.brands()
    }
    suspend fun getFlash(): FlashDTO {
//        return retrofitApi.getFlash()
        return Plug.flash()
    }
}