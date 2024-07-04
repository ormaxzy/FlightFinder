package com.example.feature_search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.core.utils.formatWithSpaces
import com.example.domain.model.TicketOffer
import com.example.feature_search.R
import com.example.feature_search.databinding.ItemFlightBinding
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

/**
 * Делегат для адаптера, который используется для отображения элементов типа [TicketOffer].
 */
fun ticketAdapterDelegate() = adapterDelegateViewBinding<TicketOffer, TicketOffer, ItemFlightBinding>(
    { layoutInflater, root -> ItemFlightBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        // Установка заголовка предложения
        binding.nameText.text = item.title
        // Установка временного диапазона предложения
        binding.timeText.text = item.time_range.joinToString(" ")
        // Форматирование и установка цены предложения
        val formattedPrice = "от ${item.price.value.formatWithSpaces()} ₽"
        binding.priceText.text = formattedPrice

        // Установка иконки статуса по id
        val statusIconResId = when (item.id) {
            1 -> R.drawable.ic_red
            10 -> R.drawable.ic_blue
            30 -> R.drawable.ic_white
            else -> R.drawable.ic_white
        }
        binding.statusIcon.setImageResource(statusIconResId)
    }
}

/**
 * Адаптер для списка предложений билетов с использованием [AsyncListDifferDelegationAdapter].
 */
class TicketAdapter : AsyncListDifferDelegationAdapter<TicketOffer>(TicketDiffCallback()) {
    init {
        delegatesManager.addDelegate(ticketAdapterDelegate())
    }
}

/**
 * Коллбэк для расчета различий между элементами списка [TicketOffer].
 */
class TicketDiffCallback : DiffUtil.ItemCallback<TicketOffer>() {
    /**
     * Проверяет, являются ли два элемента одним и тем же элементом.
     */
    override fun areItemsTheSame(oldItem: TicketOffer, newItem: TicketOffer): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * Проверяет, имеют ли два элемента одинаковое содержимое.
     */
    override fun areContentsTheSame(oldItem: TicketOffer, newItem: TicketOffer): Boolean {
        return oldItem == newItem
    }
}
