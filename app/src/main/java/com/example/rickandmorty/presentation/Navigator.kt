package com.example.rickandmorty.presentation

interface Navigator {

    fun navigateToDetails(id: Int)

    fun navigateToEpisodes()

    fun navigateBack()
}