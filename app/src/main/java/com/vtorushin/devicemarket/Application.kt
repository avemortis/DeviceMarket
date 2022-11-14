package com.vtorushin.devicemarket

import android.app.Application
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.devicemarket.di.AppComponent
import com.vtorushin.devicemarket.di.DaggerAppComponent
import com.vtorushin.homescreen.di.HomeScreenComponent
import com.vtorushin.homescreen.di.HomeScreenComponentOwner

class Application : Application(), HomeScreenComponentOwner {
    private val appComponent: AppComponent = DaggerAppComponent
        .builder()
        .build()
    private var homeComponent: HomeScreenComponent? = null
    override fun addComponent(savedStateRegistryOwner: SavedStateRegistryOwner): HomeScreenComponent {
        if (homeComponent == null) {
            homeComponent = appComponent.homeScreenComponent.create(savedStateRegistryOwner)
        }
        return homeComponent!!
    }

    override fun clear() {
        homeComponent = null
    }
}