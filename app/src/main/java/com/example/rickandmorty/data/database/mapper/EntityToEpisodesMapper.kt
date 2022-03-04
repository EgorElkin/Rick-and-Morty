package com.example.rickandmorty.data.database.mapper

import com.example.rickandmorty.data.database.entity.EpisodeEntity
import com.example.rickandmorty.domain.entity.Episode

class EntityToEpisodesMapper : (List<EpisodeEntity>) -> List<Episode> {
    override fun invoke(entities: List<EpisodeEntity>): List<Episode> {
        return entities.map {
            Episode(
                id = it.id,
                name = it.name,
                airDate = it.airDate
            )
        }
    }
}

class EpisodeToEntityMapper : (List<Episode>) -> List<EpisodeEntity> {
    override fun invoke(episodes: List<Episode>): List<EpisodeEntity> {
        return episodes.map {
            EpisodeEntity(
                id = it.id,
                name = it.name,
                airDate = it.airDate
            )
        }
    }
}