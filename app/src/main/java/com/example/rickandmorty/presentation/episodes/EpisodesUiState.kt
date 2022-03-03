package com.example.rickandmorty.presentation.episodes

import com.example.rickandmorty.domain.entity.Episode

data class EpisodesUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val episodes: List<Episode>? = null
)