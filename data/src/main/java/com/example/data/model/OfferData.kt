package com.example.data.model

import com.google.gson.annotations.SerializedName

data class OfferData(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("town") val town: String,
    @SerializedName("price") val price: PriceData
)
