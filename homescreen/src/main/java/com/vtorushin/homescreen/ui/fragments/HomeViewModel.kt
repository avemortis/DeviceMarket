package com.vtorushin.homescreen.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtorushin.homescreen.data.api.HomeScreenService
import com.vtorushin.hotsales.models.HotSalesItem
import com.vtorushin.market.data.MarketItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(service: HomeScreenService) : ViewModel() {
    var active = 0

    private val _homeStore: MutableStateFlow<List<HotSalesItem>> = MutableStateFlow(listOf())
    val homeStore = _homeStore.asStateFlow()

    private val _market: MutableStateFlow<List<MarketItem>> = MutableStateFlow(listOf())
    val market = _market.asStateFlow()

    init {
        viewModelScope.launch {
            val response = service.getHomeData()
            _homeStore.emit(response.homeStore)
            _market.emit(response.bestSeller)
        }
    }
}