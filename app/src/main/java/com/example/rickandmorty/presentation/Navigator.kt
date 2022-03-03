package com.example.rickandmorty.presentation

interface Navigator {

    fun navigateToDetails(id: Int)

    fun navigateToEpisodes(episodesIds: List<Int>)

    fun navigateBack()
}