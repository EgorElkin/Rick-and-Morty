package com.example.rickandmorty.presentation.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.domain.usecase.GetEpisodesUseCase

@Suppress("UNCHECKED_CAST")
class EpisodesViewModelFactory(
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val onBackClicked: (() -> Unit)?
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(EpisodesViewModel::class.java) -> {
                EpisodesViewModel(getEpisodesUseCase, onBackClicked) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown class name")
            }
        }
    }
}