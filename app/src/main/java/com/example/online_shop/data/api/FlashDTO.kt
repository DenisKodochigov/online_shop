package com.example.online_shop.data.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlashDTO(
    @Json(name = "flash_sale") val flash_sale: List<ProductDiscountDTO>? = null
)