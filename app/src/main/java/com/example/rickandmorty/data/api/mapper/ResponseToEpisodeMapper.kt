package com.example.rickandmorty.data.api.mapper

import com.example.rickandmorty.data.api.enitity.EpisodeResponse
import com.example.rickandmorty.domain.entity.Episode

class ResponseToEpisodeListMapper : (EpisodeResponse) -> List<Episode> {
    override fun invoke(response: EpisodeResponse): List<Episode> {
        return listOf(
            Episode(
                id = response.id,
                name = response.name,
                airDate = response.airDate
            )
        )
    }
}

class ResponseListToEpisodeListMapper : (List<EpisodeResponse>) -> List<Episode> {
    override fun invoke(response: List<EpisodeResponse>): List<Episode> {
        return response.map {
            Episode(
                id = it.id,
                name = it.name,
                airDate = it.airDate
            )
        }
    }
}