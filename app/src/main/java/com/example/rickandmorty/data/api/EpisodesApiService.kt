package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.api.enitity.EpisodeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesApiService {

    @GET(value = "episode/{episodes_ids}")
    fun getEpisodes(@Path(value = "episodes_ids") episodesIds: String): Single<List<EpisodeResponse>>

    @GET(value = "episode/{episode_id}")
    fun getOneEpisode(@Path(value = "episode_id") episodeId: Int): Single<EpisodeResponse>
}