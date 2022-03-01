package com.example.rickandmorty.di

import com.example.rickandmorty.data.api.CharactersNetworkDataSource
import com.example.rickandmorty.data.repository.CharactersRepositoryImpl
import com.example.rickandmorty.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class RepositoryModule {

    @Provides
    @Reusable
    fun provideCharactersRepository(
        charactersNetworkDataSource: CharactersNetworkDataSource
    ): CharactersRepository{
        return CharactersRepositoryImpl(charactersNetworkDataSource)
    }
}