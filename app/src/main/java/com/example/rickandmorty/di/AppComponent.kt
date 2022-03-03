package com.example.rickandmorty.di

import android.content.Context
import com.example.rickandmorty.presentation.characters.CharactersViewModelFactory
import com.example.rickandmorty.presentation.details.DetailsViewModelFactory
import com.example.rickandmorty.presentation.episodes.EpisodesViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, UseCaseModule::class, ViewModelModule::class])
interface AppComponent {

    fun getCharactersViewModelFactory(): CharactersViewModelFactory
    fun getDetailsViewModelFactory(): DetailsViewModelFactory
    fun getEpisodesViewModelFactory(): EpisodesViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}