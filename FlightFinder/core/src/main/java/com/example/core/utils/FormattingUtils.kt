package com.example.core.utils

/**
 * Функция для форматирования числа с пробелами.
 * Например, 1234567 будет преобразовано в "1 234 567".
 *
 * @return строковое представление числа с пробелами.
 */
fun Int.formatWithSpaces(): String {
    return this.toString().reversed().chunked(3).joinToString(" ").reversed()
}
