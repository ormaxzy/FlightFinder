package com.example.domain.usecase

import com.example.domain.model.TicketOffer
import com.example.domain.repository.TicketOfferRepository
import javax.inject.Inject

/**
 * UseCase для получения списка предложений билетов.
 *
 * @property ticketOfferRepository Репозиторий для получения предложений билетов.
 */
class GetTicketOffersUseCase @Inject constructor(
    private val ticketOfferRepository: TicketOfferRepository
) {

    /**
     * Выполняет получение списка предложений билетов из репозитория.
     *
     * @return Список [TicketOffer].
     */
    suspend fun execute(): List<TicketOffer> {
        return ticketOfferRepository.getTicketOffers()
    }
}
