package com.example.domain.repository

import com.example.domain.model.Offer

interface OfferRepository {
    suspend fun getOffers(): List<Offer>
}
