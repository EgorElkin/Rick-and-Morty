package com.example.rickandmorty.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.characters.CharactersFragment
import com.example.rickandmorty.presentation.details.DetailsFragment

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
        println("debug: MainActivity: navigateToDetails: $id")
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFragmentContainer, DetailsFragment.newInstance(id))
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    override fun navigateToEpisodes() {
        TODO("Not yet implemented")
    }

    override fun navigateBack() {
        onBackPressed()
    }
// <------------------ Navigator ----------------------------
}