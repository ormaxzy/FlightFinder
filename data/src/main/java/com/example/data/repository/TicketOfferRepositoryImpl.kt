package com.example.data.repository

import com.example.data.mapper.DataToDomainMapper
import com.example.data.remote.RemoteDataSource
import com.example.domain.model.TicketOffer
import com.example.domain.repository.TicketOfferRepository
import javax.inject.Inject

/**
 * Реализация репозитория для получения предложений билетов.
 *
 * @property remoteDataSource Источник данных, используемый для получения данных с удаленного сервера.
 */
class TicketOfferRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : TicketOfferRepository {

    /**
     * Получает список предложений билетов и преобразует их в доменную модель.
     *
     * @return Список [TicketOffer].
     */
    override suspend fun getTicketOffers(): List<TicketOffer> {
        return remoteDataSource.fetchTicketOffers().map { DataToDomainMapper.mapTicketOffer(it) }
    }
}
