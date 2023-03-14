package com.example.online_shop.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonFilmDTO(
    @Json(name = "filmId") val filmId:Int? = null,
    @Json(name = "nameRu") val nameRu:String? = null,
    @Json(name = "nameEn") val nameEn:String? = null,
    @Json(name = "rating") val rating:String? = null,
    @Json(name = "general") val general:Boolean? = null,
    @Json(name = "description") val description:String? = null,
    @Json(name = "professionKey") val professionKey:String? = null,
)
