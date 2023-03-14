package com.example.online_shop.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDTO(
    @Json(name = "category") val category: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "price") val price: Int? = null,
    @Json(name = "image_url") val image_url: String? = null
)
