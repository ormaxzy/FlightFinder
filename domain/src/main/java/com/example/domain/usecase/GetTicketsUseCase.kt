package com.example.domain.usecase

import com.example.domain.model.Ticket
import com.example.domain.repository.TicketRepository
import javax.inject.Inject

/**
 * UseCase для получения списка билетов.
 *
 * @property ticketRepository Репозиторий для получения билетов.
 */
class GetTicketsUseCase @Inject constructor(
    private val ticketRepository: TicketRepository
) {

    /**
     * Выполняет получение списка билетов из репозитория.
     *
     * @return Список [Ticket].
     */
    suspend fun execute(): List<Ticket> {
        return ticketRepository.getTickets()
    }
}
