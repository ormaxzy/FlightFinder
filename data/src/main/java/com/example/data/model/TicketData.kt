package com.example.data.model

import com.google.gson.annotations.SerializedName

data class TicketData(
    @SerializedName("id") val id: Int,
    @SerializedName("badge") val badge: String?,
    @SerializedName("price") val price: PriceData,
    @SerializedName("provider_name") val providerName: String,
    @SerializedName("company") val company: String,
    @SerializedName("departure") val departure: DepartureData,
    @SerializedName("arrival") val arrival: ArrivalData,
    @SerializedName("has_transfer") val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer") val hasVisaTransfer: Boolean,
    @SerializedName("luggage") val luggage: LuggageData,
    @SerializedName("hand_luggage") val handLuggage: HandLuggageData,
    @SerializedName("is_returnable") val isReturnable: Boolean,
    @SerializedName("is_exchangable") val isExchangable: Boolean
)

data class PriceData(
    @SerializedName("value") val value: Int
)

data class DepartureData(
    @SerializedName("town") val town: String,
    @SerializedName("date") val date: String,
    @SerializedName("airport") val airport: String
)

data class ArrivalData(
    @SerializedName("town") val town: String,
    @SerializedName("date") val date: String,
    @SerializedName("airport") val airport: String
)

data class LuggageData(
    @SerializedName("has_luggage") val hasLuggage: Boolean,
    @SerializedName("price") val price: PriceData?
)

data class HandLuggageData(
    @SerializedName("has_hand_luggage") val hasHandLuggage: Boolean,
    @SerializedName("size") val size: String?
)
