package com.example.rickandmorty.navigation

import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.characters.CharactersFragment
import com.example.rickandmorty.presentation.details.DetailsFragment
import com.example.rickandmorty.presentation.episodes.EpisodesFragment

class Navigator {

    var activity: AppCompatActivity? = null

    fun showCharacters(){
        activity!!.supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentContainer, CharactersFragment())
            .commitAllowingStateLoss()
    }

    fun showDetails(id: Int){
        activity!!.supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentContainer, DetailsFragment.newInstance(id))
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    fun showEpisodes(episodesIds: List<Int>) {
        activity!!.supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentContainer, EpisodesFragment.newInstance(episodesIds))
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    fun navigateBack() {
        activity!!.onBackPressed()
    }
}