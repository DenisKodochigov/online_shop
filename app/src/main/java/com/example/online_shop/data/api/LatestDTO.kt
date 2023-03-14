package com.example.online_shop.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LatestDTO(
    @Json(name = "latest") val latest: List<ProductDTO>? = null
)