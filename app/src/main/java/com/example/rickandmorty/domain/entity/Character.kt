package com.example.rickandmorty.domain.entity

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val location: Location,
    val imageUrl: String,
    val episodes: List<String>,
)