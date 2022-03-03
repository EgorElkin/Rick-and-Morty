package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.api.CharactersNetworkDataSource
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.domain.repository.CharactersRepository
import io.reactivex.Observable

class CharactersRepositoryImpl(
    private val charactersNetworkDataSource: CharactersNetworkDataSource
) : CharactersRepository {

    override fun getCharacters(pageNumber: Int): Observable<List<Character>> {
        return charactersNetworkDataSource.loadCharacters(pageNumber).toObservable()
    }

    override fun getDetails(characterId: Int): Observable<Character> {
        return charactersNetworkDataSource.loadDetails(characterId).toObservable()
    }
}