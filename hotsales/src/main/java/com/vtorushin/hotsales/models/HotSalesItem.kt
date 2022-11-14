package com.vtorushin.hotsales.models

import com.google.gson.annotations.SerializedName

data class HotSalesItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("is_buy")
    val isBuy: Boolean
)
