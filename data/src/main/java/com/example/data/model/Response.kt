package com.example.data.model

import com.google.gson.annotations.SerializedName

/**
 * Ответ сервера, содержащий список предложений.
 *
 * @property offers Список предложений.
 */
data class OffersResponse(
    @SerializedName("offers") val offers: List<OfferData>
)

/**
 * Ответ сервера, содержащий список предложений билетов.
 *
 * @property ticketOffers Список предложений билетов.
 */
data class TicketOffersResponse(
    @SerializedName("tickets_offers") val ticketOffers: List<TicketOfferData>
)

/**
 * Ответ сервера, содержащий список билетов.
 *
 * @property tickets Список билетов.
 */
data class TicketsResponse(
    @SerializedName("tickets") val tickets: List<TicketData>
)