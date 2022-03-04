package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.api.CharactersNetworkDataSource
import com.example.rickandmorty.data.database.CharactersLocalDataSource
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.domain.entity.Location
import com.example.rickandmorty.domain.repository.CharactersRepository
import io.reactivex.Observable
import io.reactivex.Single

class CharactersRepositoryImpl(
    private val networkDataSource: CharactersNetworkDataSource,
    private val localDataSource: CharactersLocalDataSource
) : CharactersRepository {

    override fun getCharacters(pageNumber: Int): Observable<List<Character>> {
        if(pageNumber == 1){
            return localDataSource.getAllCharactersBrief()
                .flatMapObservable { localList ->
                    networkDataSource.loadCharacters(pageNumber)
                        .toObservable()
                        .flatMapSingle { apiList ->
                            localDataSource.deleteAllCharactersBrief()
                                .andThen(
                                    localDataSource.addAllCharactersBrief(apiList)
                                        .andThen(
                                            Single.just(apiList)
                                        )
                                )
                        }
                        .startWith(localList)
                }
        } else {
            return networkDataSource.loadCharacters(pageNumber).toObservable()
        }
    }

    override fun getDetails(characterId: Int): Observable<Character> {
        return localDataSource.getDetails(characterId)
            .onErrorReturnItem(Character(id = 0, name = "", status = "", species = "", location = Location(""), imageUrl = "", episodes = emptyList()))
            .flatMapObservable { local ->
                networkDataSource.loadDetails(characterId)
                    .toObservable()
                    .filter { api ->
                        local != api
                    }
                    .flatMapSingle { api ->
                        localDataSource.addDetails(api)
                            .andThen(Single.just(api))
                    }
                    .startWith(local)
            }
    }
}