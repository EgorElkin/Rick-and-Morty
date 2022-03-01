package com.example.rickandmorty.presentation.characters.pagination

class Paginator {

    var currentPage: Int = 1
        private set
    val isFirstPage: Boolean
        get() = currentPage == 1

    fun pageLoaded() {
        currentPage++
    }

    fun reset() {
        currentPage = 1
    }
}