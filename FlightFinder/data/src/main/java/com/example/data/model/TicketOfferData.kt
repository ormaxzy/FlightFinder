package com.example.data.model

import com.google.gson.annotations.SerializedName

data class TicketOfferData(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("time_range") val timeRange: List<String>,
    @SerializedName("price") val price: PriceData
)
