package com.vtorushin.homescreen.di

import androidx.savedstate.SavedStateRegistryOwner

interface HomeScreenComponentOwner {
    fun addComponent(savedStateRegistryOwner: SavedStateRegistryOwner): HomeScreenComponent
    fun clear()
}