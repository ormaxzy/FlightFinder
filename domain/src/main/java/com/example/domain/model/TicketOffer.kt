package com.example.domain.model

data class TicketOffer(
    val id: Int,
    val title: String,
    val time_range: List<String>,
    val price: Price
)
