package com.example.rickandmorty.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.App
import com.example.rickandmorty.R
import com.example.rickandmorty.navigation.GodFlowCoordinator
import com.example.rickandmorty.navigation.Navigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(){

    @Inject lateinit var navigator: Navigator
    @Inject lateinit var coordinator: GodFlowCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).appComponent.inject(this)
        navigator.activity = this
        coordinator.showList()
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.activity = null
    }
}