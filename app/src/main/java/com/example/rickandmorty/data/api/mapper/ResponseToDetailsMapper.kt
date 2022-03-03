package com.example.rickandmorty.data.api.mapper

import com.example.rickandmorty.data.api.enitity.DetailsResponse
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.domain.entity.Location

class ResponseToDetailsMapper : (DetailsResponse) -> Character {

    override fun invoke(response: DetailsResponse): Character {
        return response.run {
            Character(
                id = id,
                name = name,
                status = status,
                species = species,
                location = Location(location.name),
                imageUrl = imageUrl,
                episodes = episodes
            )
        }
    }
}