package com.example.rickandmorty.presentation.characters

import com.example.rickandmorty.presentation.characters.adapter.item.CharacterItem

data class CharactersUiState(
    val isLoading: Boolean = false,
    val isRepeatAvailable: Boolean = false,
    val characters: List<CharacterItem> = emptyList(),
)

data class CharactersUiEvents(
    val errorMessageRes: Int? = null
)