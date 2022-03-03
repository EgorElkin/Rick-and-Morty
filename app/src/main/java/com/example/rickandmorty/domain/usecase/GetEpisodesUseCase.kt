package com.example.rickandmorty.domain.usecase

import com.example.rickandmorty.domain.entity.Episode
import com.example.rickandmorty.domain.repository.EpisodesRepository
import io.reactivex.Observable

interface GetEpisodesUseCase : (List<Int>) -> Observable<List<Episode>>

class GetEpisodesUseCaseImpl(private val episodesRepository: EpisodesRepository) : GetEpisodesUseCase{

    override fun invoke(ids: List<Int>): Observable<List<Episode>> {
        return episodesRepository.getEpisodes(ids)
    }

}