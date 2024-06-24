package com.example.domain.usecase

import com.example.domain.model.Offer
import com.example.domain.repository.OfferRepository
import javax.inject.Inject

/**
 * UseCase для получения списка предложений.
 *
 * @property offerRepository Репозиторий для получения предложений.
 */
class GetOffersUseCase @Inject constructor(
    private val offerRepository: OfferRepository
) {

    /**
     * Выполняет получение списка предложений из репозитория.
     *
     * @return Список [Offer].
     */
    suspend fun execute(): List<Offer> {
        return offerRepository.getOffers()
    }
}
