package com.example.feature_tickets.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.example.core.utils.formatWithSpaces
import com.example.domain.model.Ticket
import com.example.feature_tickets.databinding.ItemTicketBinding
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * Делегат адаптера для отображения элементов типа [Ticket].
 */
fun ticketAdapterDelegate() = adapterDelegateViewBinding<Ticket, Ticket, ItemTicketBinding>(
    { layoutInflater, root -> ItemTicketBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        // Установка форматированной цены
        binding.priceTextView.text = item.price.value.formatWithSpaces()

        val departureDate = parseDate(item.departure.date)
        val arrivalDate = parseDate(item.arrival.date)

        // Установка времени и места отправления и прибытия
        binding.departureTextView.text = formatTime(departureDate)
        binding.departureAirPortTextView.text = item.departure.airport
        binding.arrivalTextView.text = formatTime(arrivalDate)
        binding.arrivalAirPortTextView.text = item.arrival.airport

        // Установка времени в пути и наличия пересадок
        binding.flightTimeTextView.text = calculateFlightTime(departureDate, arrivalDate)
        binding.transferTimeTextView.text = if (item.has_transfer) "" else "Без пересадок"
        binding.slashTextView.visibility = if (item.has_transfer) View.GONE else View.VISIBLE

        // Установка бейджа, если он есть
        if (item.badge?.isNotEmpty() == true) {
            binding.badgeCardView.visibility = View.VISIBLE
            binding.badgeTextView.text = item.badge
        } else {
            binding.badgeCardView.visibility = View.GONE
        }
    }
}

/**
 * Адаптер для списка билетов с использованием [AsyncListDifferDelegationAdapter].
 */
class TicketAdapter : AsyncListDifferDelegationAdapter<Ticket>(TicketDiffCallback()) {
    init {
        delegatesManager.addDelegate(ticketAdapterDelegate())
    }
}

/**
 * Коллбэк для расчета различий между элементами списка [Ticket].
 */
class TicketDiffCallback : DiffUtil.ItemCallback<Ticket>() {
    /**
     * Проверяет, являются ли два элемента одним и тем же элементом.
     */
    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * Проверяет, имеют ли два элемента одинаковое содержимое.
     */
    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem == newItem
    }
}

// Дополнительные функции для форматирования

/**
 * Форматирует дату в строку времени.
 */
private fun formatTime(date: Date): String {
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return dateFormat.format(date)
}

/**
 * Вычисляет время в пути между двумя датами.
 */
private fun calculateFlightTime(departureDate: Date, arrivalDate: Date): String {
    val diff = arrivalDate.time - departureDate.time
    val hours = (diff / (1000 * 60 * 60)).toInt()
    val minutes = (diff / (1000 * 60)) % 60
    return "${hours}ч ${minutes}м в пути"
}

/**
 * Преобразует строку даты в объект [Date].
 */
private fun parseDate(dateString: String): Date {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    return format.parse(dateString)!!
}
