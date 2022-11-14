package com.vtorushin.devicemarket.di

import com.vtorushin.homescreen.di.HomeScreenComponent
import com.vtorushin.homescreen.di.HomeScreenScope
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {
    @HomeScreenScope
    val homeScreenComponent: HomeScreenComponent.Factory
}