package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.api.mapper.ResponseListToEpisodeListMapper
import com.example.rickandmorty.data.api.mapper.ResponseToEpisodeListMapper
import com.example.rickandmorty.domain.entity.Episode
import io.reactivex.Single

class EpisodesNetworkDataSource(private val episodesApiService: EpisodesApiService) {

    fun loadEpisodes(episodesIds: List<Int>): Single<List<Episode>> {
        return if(episodesIds.size == 1){
            episodesApiService.getOneEpisode(episodesIds[0]).map(ResponseToEpisodeListMapper())
        } else {
            episodesApiService.getEpisodes(episodesIds.joinToString(separator = ",")).map(ResponseListToEpisodeListMapper())
        }
    }
}