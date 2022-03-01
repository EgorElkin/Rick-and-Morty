package com.example.rickandmorty.di

import com.example.rickandmorty.domain.usecase.GetCharactersUseCase
import com.example.rickandmorty.presentation.characters.CharactersViewModelFactory
import com.example.rickandmorty.presentation.characters.pagination.Paginator
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providePaginator(): Paginator = Paginator()

    @Provides
    fun providesCharactersViewModelFactory(
        getCharactersUseCase: GetCharactersUseCase,
        paginator: Paginator
    ): CharactersViewModelFactory{
        return CharactersViewModelFactory(getCharactersUseCase, paginator)
    }
}