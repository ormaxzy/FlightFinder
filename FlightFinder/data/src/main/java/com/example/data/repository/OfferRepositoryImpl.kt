package com.example.data.repository

import com.example.data.mapper.DataToDomainMapper
import com.example.data.remote.RemoteDataSource
import com.example.domain.model.Offer
import com.example.domain.repository.OfferRepository
import javax.inject.Inject

/**
 * Реализация репозитория для получения предложений.
 *
 * @property remoteDataSource Источник данных, используемый для получения данных с удаленного сервера.
 */
class OfferRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : OfferRepository {

    /**
     * Получает список предложений и преобразует их в доменную модель.
     *
     * @return Список [Offer].
     */
    override suspend fun getOffers(): List<Offer> {
        return remoteDataSource.fetchOffers().map { DataToDomainMapper.mapOffer(it) }
    }
}
