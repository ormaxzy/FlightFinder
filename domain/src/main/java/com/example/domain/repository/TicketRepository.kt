package com.example.domain.repository

import com.example.domain.model.Ticket

interface TicketRepository {
    suspend fun getTickets(): List<Ticket>
}
