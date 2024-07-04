package com.example.feature_main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.core.utils.formatWithSpaces
import com.example.domain.model.Offer
import com.example.feature_main.R
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.example.feature_main.databinding.ItemOfferBinding

/**
 * Функция для создания делегата адаптера для [Offer].
 */
fun offerAdapterDelegate() = adapterDelegateViewBinding<Offer, Offer, ItemOfferBinding>(
    { layoutInflater, root -> ItemOfferBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        // Установка заголовка предложения
        binding.tvTitle.text = item.title
        // Установка города предложения
        binding.tvTown.text = item.town
        // Форматирование и установка цены предложения
        val formattedPrice = "от ${item.price.value.formatWithSpaces()} ₽"
        binding.tvPrice.text = formattedPrice

        // Установка изображения по id
        val imageResId = when (item.id) {
            1 -> R.drawable.image_dora
            2 -> R.drawable.image_lera_socrat
            else -> R.drawable.placeholder_image
        }
        binding.ivImage.setImageResource(imageResId)
    }
}

/**
 * Адаптер для списка предложений с использованием [AsyncListDifferDelegationAdapter].
 */
class OfferAdapter : AsyncListDifferDelegationAdapter<Offer>(OfferDiffCallback()) {
    init {
        delegatesManager.addDelegate(offerAdapterDelegate())
    }
}

/**
 * Коллбэк для расчета различий между элементами списка [Offer].
 */
class OfferDiffCallback : DiffUtil.ItemCallback<Offer>() {
    /**
     * Проверяет, являются ли два элемента одним и тем же элементом.
     */
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem.id == newItem.id
    }

    /**
     * Проверяет, имеют ли два элемента одинаковое содержимое.
     */
    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }
}
