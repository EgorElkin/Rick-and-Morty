package com.example.rickandmorty.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rickandmorty.R
import com.example.rickandmorty.presentation.characters.CharactersFragment

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
        TODO("Not yet implemented")
    }

    override fun navigateToEpisodes() {
        TODO("Not yet implemented")
    }

    override fun navigateBack() {
        TODO("Not yet implemented")
    }
// <------------------ Navigator ----------------------------
}