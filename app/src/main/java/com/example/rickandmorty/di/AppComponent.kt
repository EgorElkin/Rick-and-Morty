package com.example.rickandmorty.di

import android.content.Context
import com.example.rickandmorty.presentation.characters.CharactersViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, UseCaseModule::class, ViewModelModule::class])
interface AppComponent {

    fun getCharactersViewModelFactory(): CharactersViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}