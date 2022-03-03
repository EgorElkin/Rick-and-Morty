package com.example.rickandmorty.di

import android.content.Context
import com.example.rickandmorty.presentation.characters.CharactersViewModelFactory
import com.example.rickandmorty.presentation.details.DetailsViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, UseCaseModule::class, ViewModelModule::class])
interface AppComponent {

    fun getCharactersViewModelFactory(): CharactersViewModelFactory
    fun getDetailsViewModelFactory(): DetailsViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}