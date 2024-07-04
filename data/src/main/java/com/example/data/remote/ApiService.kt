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
    @GET("u/0/uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download")
    suspend fun getOffersResponse(): OffersResponse

    /**
     * Получает список предложений билетов.
     *
     * @return [TicketOffersResponse] содержащий список предложений билетов.
     */
    @GET("u/0/uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta&export=download")
    suspend fun getTicketOffersResponse(): TicketOffersResponse

    /**
     * Получает список билетов.
     *
     * @return [TicketsResponse] содержащий список билетов.
     */
    @GET("uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
    suspend fun getTicketsResponse(): TicketsResponse
}
