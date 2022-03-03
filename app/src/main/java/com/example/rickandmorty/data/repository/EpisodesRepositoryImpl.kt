package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.api.EpisodesNetworkDataSource
import com.example.rickandmorty.domain.entity.Episode
import com.example.rickandmorty.domain.repository.EpisodesRepository
import io.reactivex.Observable

class EpisodesRepositoryImpl(
    private val episodesNetworkDataSource: EpisodesNetworkDataSource
) : EpisodesRepository {

    override fun getEpisodes(episodesIds: List<Int>): Observable<List<Episode>> {
        return episodesNetworkDataSource.loadEpisodes(episodesIds).toObservable()
    }
}