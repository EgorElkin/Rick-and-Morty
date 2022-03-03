package com.example.rickandmorty.di

import com.example.rickandmorty.domain.repository.CharactersRepository
import com.example.rickandmorty.domain.repository.EpisodesRepository
import com.example.rickandmorty.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class UseCaseModule {

    @Provides
    @Reusable
    fun provideGetCharactersUseCase(charactersRepository: CharactersRepository): GetCharactersUseCase{
        return GetCharactersUseCaseImpl(charactersRepository)
    }

    @Provides
    @Reusable
    fun provideGetDetailsUseCase(charactersRepository: CharactersRepository): GetDetailsUseCase{
        return GetDetailsUseCaseImpl(charactersRepository)
    }

    @Provides
    @Reusable
    fun provideGetEpisodesUseCase(episodesRepository: EpisodesRepository): GetEpisodesUseCase{
        return GetEpisodesUseCaseImpl(episodesRepository)
    }
}