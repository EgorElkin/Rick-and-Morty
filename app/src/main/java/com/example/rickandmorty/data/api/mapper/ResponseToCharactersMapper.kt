package com.example.rickandmorty.data.api.mapper

import com.example.rickandmorty.data.api.enitity.CharactersResponse
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.domain.entity.Location

class ResponseToCharactersMapper : (CharactersResponse) -> List<Character>{

    override fun invoke(response: CharactersResponse): List<Character> {
        return response.results.map {
            Character(
                id = it.id,
                name = it.name,
                status = it.status,
                species = it.species,
                location = Location(name = it.location.name),
                imageUrl = it.imageUrl,
                episodes = it.episodes,
            )
        }
    }
}