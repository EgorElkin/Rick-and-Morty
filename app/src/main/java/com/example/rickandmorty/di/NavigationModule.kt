package com.example.rickandmorty.di

import com.example.rickandmorty.navigation.GodFlowCoordinator
import com.example.rickandmorty.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    fun provideNavigator(): Navigator{
        return Navigator()
    }

    @Provides
    @Singleton
    fun provideGodFlowCoordinator(navigator: Navigator): GodFlowCoordinator{
        return GodFlowCoordinator(navigator)
    }
}