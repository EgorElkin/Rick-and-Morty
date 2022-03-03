package com.example.rickandmorty.di

import com.example.rickandmorty.domain.repository.CharactersRepository
import com.example.rickandmorty.domain.usecase.GetCharactersUseCase
import com.example.rickandmorty.domain.usecase.GetCharactersUseCaseImpl
import com.example.rickandmorty.domain.usecase.GetDetailsUseCase
import com.example.rickandmorty.domain.usecase.GetDetailsUseCaseImpl
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
}