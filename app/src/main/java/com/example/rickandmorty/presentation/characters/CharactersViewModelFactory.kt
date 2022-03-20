package com.example.rickandmorty.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.domain.usecase.GetCharactersUseCase
import com.example.rickandmorty.presentation.characters.pagination.Paginator

@Suppress("UNCHECKED_CAST")
class CharactersViewModelFactory(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val paginator: Paginator,
    private var onCharacterClicked: ((Int) -> Unit)?
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(CharactersViewModel::class.java) -> {
                CharactersViewModel(getCharactersUseCase, paginator, onCharacterClicked) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown class name")
            }
        }
    }
}