package com.example.rickandmorty.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.characters.CharactersFragment
import com.example.rickandmorty.presentation.details.DetailsFragment
import com.example.rickandmorty.presentation.episodes.EpisodesFragment

class MainActivity : AppCompatActivity(), Navigator{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.mainFragmentContainer, CharactersFragment())
                .commitAllowingStateLoss()
        }
    }

// ------------------ Navigator ---------------------------->
    override fun navigateToDetails(id: Int) {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentContainer, DetailsFragment.newInstance(id))
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    override fun navigateToEpisodes(episodesIds: List<Int>) {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentContainer, EpisodesFragment.newInstance(episodesIds))
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    override fun navigateBack() {
        onBackPressed()
    }
// <------------------ Navigator ----------------------------
}