package com.vtorushin.homescreen.di

import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.homescreen.ViewModelFactory
import com.vtorushin.homescreen.data.api.HOME_SERVICE_BASE_URL
import com.vtorushin.homescreen.data.api.HomeScreenService
import com.vtorushin.homescreen.ui.fragments.HomeViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class HomeScreenModule {
    @Provides
    @HomeScreenScope
    fun provideHomeScreenService(): HomeScreenService {
        return Retrofit.Builder()
            .baseUrl(HOME_SERVICE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @HomeScreenScope
    fun provideViewModel(savedStateRegistryOwner: SavedStateRegistryOwner): HomeViewModel {
        return ViewModelFactory(savedStateRegistryOwner, provideHomeScreenService()).create(
            HomeViewModel::class.java
        )
    }
}