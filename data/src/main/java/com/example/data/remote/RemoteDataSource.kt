package com.example.data.remote

import com.example.data.model.OfferData
import com.example.data.model.TicketData
import com.example.data.model.TicketOfferData
import javax.inject.Inject

/**
 * Класс для получения данных с удаленного сервера через [ApiService].
 *
 * @property apiService API-сервис для выполнения сетевых запросов.
 */
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    /**
     * Получает список предложений с сервера.
     *
     * @return Список [OfferData].
     */
    suspend fun fetchOffers(): List<OfferData> = apiService.getOffersResponse().offers

    /**
     * Получает список предложений билетов с сервера.
     *
     * @return Список [TicketOfferData].
     */
    suspend fun fetchTicketOffers(): List<TicketOfferData> = apiService.getTicketOffersResponse().ticketOffers

    /**
     * Получает список билетов с сервера.
     *
     * @return Список [TicketData].
     */
    suspend fun fetchTickets(): List<TicketData> = apiService.getTicketsResponse().tickets
}
