package com.example.core.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesHelper {

    private const val PREF_NAME = "input_values"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveInputValues(context: Context, from: String, to: String) {
        val sharedPref = getSharedPreferences(context)
        with(sharedPref.edit()) {
            putString("from", from)
            putString("to", to)
            apply()
        }
    }

    fun loadInputValues(context: Context): Pair<String, String> {
        val sharedPref = getSharedPreferences(context)
        val from = sharedPref.getString("from", "") ?: ""
        val to = sharedPref.getString("to", "") ?: ""
        return Pair(from, to)
    }
}
