package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.entity.Episode
import io.reactivex.Observable

interface EpisodesRepository {

    fun getEpisodes(episodesIds: List<Int>): Observable<List<Episode>>
}