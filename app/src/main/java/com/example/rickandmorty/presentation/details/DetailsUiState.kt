package com.example.rickandmorty.presentation.details

import com.example.rickandmorty.domain.entity.Character

data class DetailsUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val userInfo: Character? = null,
)

data class DetailsUiEvents(
    val errorRes: Int? = null
)