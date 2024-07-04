package com.example.domain.repository

import com.example.domain.model.TicketOffer

interface TicketOfferRepository {
    suspend fun getTicketOffers(): List<TicketOffer>
}
