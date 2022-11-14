package com.vtorushin.homescreen.data.api

import com.vtorushin.homescreen.data.api.model.HomeScreenResponseBody
import retrofit2.http.GET

interface HomeScreenService {
    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getHomeData(): HomeScreenResponseBody
}

const val HOME_SERVICE_BASE_URL = "https://run.mocky.io"