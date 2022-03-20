package com.example.rickandmorty.di

import com.example.rickandmorty.domain.usecase.GetCharactersUseCase
import com.example.rickandmorty.domain.usecase.GetDetailsUseCase
import com.example.rickandmorty.domain.usecase.GetEpisodesUseCase
import com.example.rickandmorty.navigation.GodFlowCoordinator
import com.example.rickandmorty.presentation.characters.CharactersViewModelFactory
import com.example.rickandmorty.presentation.characters.pagination.Paginator
import com.example.rickandmorty.presentation.details.DetailsViewModelFactory
import com.example.rickandmorty.presentation.episodes.EpisodesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providePaginator(): Paginator = Paginator()

    @Provides
    fun providesCharactersViewModelFactory(
        getCharactersUseCase: GetCharactersUseCase,
        paginator: Paginator,
        godFlowCoordinator: GodFlowCoordinator
    ): CharactersViewModelFactory{
        return CharactersViewModelFactory(getCharactersUseCase, paginator, godFlowCoordinator::showDetails)
    }

    @Provides
    fun providesDetailsViewModelFactory(
        getDetailsUseCase: GetDetailsUseCase,
        godFlowCoordinator: GodFlowCoordinator
    ) : DetailsViewModelFactory{
        return DetailsViewModelFactory(getDetailsUseCase, godFlowCoordinator::showEpisodes, godFlowCoordinator::backNavigation)
    }

    @Provides
    fun providesEpisodesViewModelFactory(
        getEpisodesUseCase: GetEpisodesUseCase,
        godFlowCoordinator: GodFlowCoordinator
    ) : EpisodesViewModelFactory {
        return EpisodesViewModelFactory(getEpisodesUseCase, godFlowCoordinator::backNavigation)
    }
}