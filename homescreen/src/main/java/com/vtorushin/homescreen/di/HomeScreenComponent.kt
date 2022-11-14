package com.vtorushin.homescreen.di

import androidx.fragment.app.Fragment
import androidx.savedstate.SavedStateRegistryOwner
import com.vtorushin.homescreen.ViewModelFactory
import com.vtorushin.homescreen.data.api.HomeScreenService
import com.vtorushin.homescreen.ui.fragments.HomeViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@HomeScreenScope
@Subcomponent(modules = [HomeScreenModule::class])
interface HomeScreenComponent {
    fun service(): HomeScreenService
    fun viewModelFactory(): ViewModelFactory.Factory
    fun viewModel(): HomeViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance savedStateRegistryOwner: SavedStateRegistryOwner
        ): HomeScreenComponent
    }
}

internal fun Fragment.component() = (requireContext().applicationContext as HomeScreenComponentOwner)
    .addComponent(this)