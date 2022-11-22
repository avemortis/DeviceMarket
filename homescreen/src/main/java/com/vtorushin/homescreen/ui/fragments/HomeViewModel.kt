package com.vtorushin.homescreen.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vtorushin.core.models.Resource
import com.vtorushin.homescreen.data.api.HomeScreenService
import com.vtorushin.homescreen.data.api.model.HomeScreenResponseBody
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(service: HomeScreenService) : ViewModel() {
    var active = 0

    private val _dataSource: MutableStateFlow<Resource<HomeScreenResponseBody>> = MutableStateFlow(Resource.loading())
    val dataSource = _dataSource.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val response = service.getHomeData()
                _dataSource.emit(Resource.complete(response))
            } catch (e: Exception) {
                _dataSource.emit(Resource.error(e.message?: "Error!"))
            }
        }
    }
}