package com.example.rickandmorty.navigation

class GodFlowCoordinator(private val navigator: Navigator) {

    fun showList(){
        navigator.showCharacters()
    }

    fun showDetails(id: Int){
        navigator.showDetails(id)
    }

    fun showEpisodes(episodesIds: List<Int>) {
        navigator.showEpisodes(episodesIds)
    }

    fun backNavigation(){
        navigator.navigateBack()
    }
}