package com.example.rickandmorty.di

import android.content.Context
import com.example.rickandmorty.presentation.MainActivity
import com.example.rickandmorty.presentation.characters.CharactersViewModelFactory
import com.example.rickandmorty.presentation.details.DetailsViewModelFactory
import com.example.rickandmorty.presentation.episodes.EpisodesViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, RepositoryModule::class, UseCaseModule::class, ViewModelModule::class, NavigationModule::class])
interface AppComponent {

    fun getCharactersViewModelFactory(): CharactersViewModelFactory
    fun getDetailsViewModelFactory(): DetailsViewModelFactory
    fun getEpisodesViewModelFactory(): EpisodesViewModelFactory

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}