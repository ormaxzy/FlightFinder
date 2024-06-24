package com.example.feature_search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TicketOffer
import com.example.domain.usecase.GetTicketOffersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getTicketOffersUseCase: GetTicketOffersUseCase
) : ViewModel() {

    private val _tickets = MutableStateFlow<List<TicketOffer>>(emptyList())
    val tickets: StateFlow<List<TicketOffer>> get() = _tickets

    private val _fromValue = MutableStateFlow("")
    val fromValue: StateFlow<String> get() = _fromValue

    private val _toValue = MutableStateFlow("")
    val toValue: StateFlow<String> get() = _toValue

    private val _departureDate = MutableStateFlow(Date())
    val departureDate: StateFlow<Date> get() = _departureDate

    private val _returnDate = MutableStateFlow<Date?>(null)
    val returnDate: StateFlow<Date?> get() = _returnDate

    init {
        fetchTickets()
    }

    /**
     * Запросить список предложений билетов.
     */
    private fun fetchTickets() {
        viewModelScope.launch {
            _tickets.value = getTicketOffersUseCase.execute()
        }
    }

    /**
     * Обновить значение пункта отправления.
     */
    fun updateFromValue(value: String) {
        _fromValue.value = value
    }

    /**
     * Обновить значение пункта назначения.
     */
    fun updateToValue(value: String) {
        _toValue.value = value
    }

    /**
     * Обновить дату отправления.
     */
    fun updateDepartureDate(date: Date) {
        _departureDate.value = date
    }

    /**
     * Обновить дату возвращения.
     */
    fun updateReturnDate(date: Date?) {
        _returnDate.value = date
    }

    /**
     * Поменять местами пункт отправления и пункт назначения.
     */
    fun swapLocations() {
        val from = _fromValue.value
        val to = _toValue.value
        _fromValue.value = to
        _toValue.value = from
    }
}
