package com.example.feature_main.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.SharedPreferencesHelper
import com.example.domain.model.Offer
import com.example.domain.usecase.GetOffersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getOffersUseCase: GetOffersUseCase
) : ViewModel() {

    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers: StateFlow<List<Offer>> get() = _offers

    private val _fromValue = MutableStateFlow("")
    val fromValue: StateFlow<String> get() = _fromValue

    private val _toValue = MutableStateFlow("")
    val toValue: StateFlow<String> get() = _toValue

    init {
        fetchOffers()
    }

    private fun fetchOffers() {
        viewModelScope.launch {
            _offers.value = getOffersUseCase.execute()
        }
    }

    fun updateFromValue(value: String) {
        _fromValue.value = value
    }

    fun loadSavedInputValues(context: Context) {
        val (from, to) = SharedPreferencesHelper.loadInputValues(context)
        _fromValue.value = from
        _toValue.value = to
    }

    fun saveInputValues(context: Context) {
        SharedPreferencesHelper.saveInputValues(context, _fromValue.value, _toValue.value)
    }
}
