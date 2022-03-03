package com.example.rickandmorty.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.domain.usecase.GetDetailsUseCase
import com.example.rickandmorty.presentation.characters.CharactersViewModel

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(DetailsViewModel::class.java) -> {
                DetailsViewModel(getDetailsUseCase) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown class name")
            }
        }
    }
}