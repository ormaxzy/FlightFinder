package com.example.feature_tickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Ticket
import com.example.domain.usecase.GetTicketsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor(
    private val getTicketsUseCase: GetTicketsUseCase
) : ViewModel() {

    private val _tickets = MutableStateFlow<List<Ticket>>(emptyList())
    val tickets: StateFlow<List<Ticket>> get() = _tickets

    init {
        fetchTickets()
    }

    /**
     * Запросить список билетов.
     */
    private fun fetchTickets() {
        viewModelScope.launch {
            val ticketList = getTicketsUseCase.execute()
            _tickets.value = ticketList
        }
    }
}
