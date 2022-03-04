package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.api.EpisodesNetworkDataSource
import com.example.rickandmorty.data.database.EpisodesLocalDataSource
import com.example.rickandmorty.domain.entity.Episode
import com.example.rickandmorty.domain.repository.EpisodesRepository
import io.reactivex.Observable
import io.reactivex.Single

class EpisodesRepositoryImpl(
    private val networkDataSource: EpisodesNetworkDataSource,
    private val localDataSource: EpisodesLocalDataSource
) : EpisodesRepository {

    override fun getEpisodes(episodesIds: List<Int>): Observable<List<Episode>> {
        return localDataSource.getEpisodes(episodesIds)
            .flatMapObservable { localList ->
                networkDataSource.loadEpisodes(episodesIds)
                    .toObservable()
                    .filter { apiList ->
                        localList != apiList
                    }
                    .flatMapSingle { apiList ->
                        localDataSource.addEpisodes(apiList)
                            .andThen(
                                Single.just(apiList)
                            )
                    }
                    .startWith(localList)
            }
    }
}