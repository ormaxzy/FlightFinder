package com.example.data.repository

import com.example.data.mapper.DataToDomainMapper
import com.example.data.remote.RemoteDataSource
import com.example.domain.model.Ticket
import com.example.domain.repository.TicketRepository
import javax.inject.Inject

/**
 * Реализация репозитория для получения билетов.
 *
 * @property remoteDataSource Источник данных, используемый для получения данных с удаленного сервера.
 */
class TicketRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : TicketRepository {

    /**
     * Получает список билетов и преобразует их в доменную модель.
     *
     * @return Список [Ticket].
     */
    override suspend fun getTickets(): List<Ticket> {
        return remoteDataSource.fetchTickets().map { DataToDomainMapper.mapTicket(it) }
    }
}
