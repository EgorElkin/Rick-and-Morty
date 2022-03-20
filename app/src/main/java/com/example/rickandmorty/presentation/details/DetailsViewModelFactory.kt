package com.example.rickandmorty.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.domain.usecase.GetDetailsUseCase

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(
    private val getDetailsUseCase: GetDetailsUseCase,
    private var onEpisodesClicked: ((List<Int>) -> Unit)?,
    private var onBackClicked: (() -> Unit)?,
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(DetailsViewModel::class.java) -> {
                DetailsViewModel(getDetailsUseCase, onEpisodesClicked, onBackClicked) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown class name")
            }
        }
    }
}