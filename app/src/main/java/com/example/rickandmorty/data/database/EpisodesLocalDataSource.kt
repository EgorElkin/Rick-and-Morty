package com.example.rickandmorty.data.database

import com.example.rickandmorty.data.database.dao.EpisodeDao
import com.example.rickandmorty.data.database.mapper.EntityToEpisodesMapper
import com.example.rickandmorty.data.database.mapper.EpisodeToEntityMapper
import com.example.rickandmorty.domain.entity.Episode
import io.reactivex.Completable
import io.reactivex.Single

class EpisodesLocalDataSource(private val episodeDao: EpisodeDao) {

    fun getEpisodes(ids: List<Int>): Single<List<Episode>> = episodeDao.getEpisodes(ids).map(EntityToEpisodesMapper())

    fun addEpisodes(episodes: List<Episode>): Completable = episodeDao.addEpisodes(EpisodeToEntityMapper().invoke(episodes))
}