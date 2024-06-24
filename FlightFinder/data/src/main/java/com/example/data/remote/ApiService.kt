package com.example.data.remote

import com.example.data.model.OffersResponse
import com.example.data.model.TicketOffersResponse
import com.example.data.model.TicketsResponse
import retrofit2.http.GET



/**
 * API-сервис для получения данных с сервера.
 */
interface ApiService {

    /**
     * Получает список предложений.
     *
     * @return [OffersResponse] содержащий список предложений.
     */
    @GET("ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getOffersResponse(): OffersResponse

    /**
     * Получает список предложений билетов.
     *
     * @return [TicketOffersResponse] содержащий список предложений билетов.
     */
    @GET("38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getTicketOffersResponse(): TicketOffersResponse

    /**
     * Получает список билетов.
     *
     * @return [TicketsResponse] содержащий список билетов.
     */
    @GET("c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTicketsResponse(): TicketsResponse
}
