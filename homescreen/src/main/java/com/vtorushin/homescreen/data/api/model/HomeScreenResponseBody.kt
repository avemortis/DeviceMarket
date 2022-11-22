package com.vtorushin.homescreen.data.api.model

import com.google.gson.annotations.SerializedName
import com.vtorushin.hotsales.models.HotSalesItem
import com.vtorushin.market.entities.MarketItem

data class HomeScreenResponseBody(
    @SerializedName("home_store")
    val homeStore: List<HotSalesItem>,
    @SerializedName("best_seller")
    val bestSeller: List<MarketItem>
)