package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.api.mapper.ResponseToCharactersMapper
import com.example.rickandmorty.domain.entity.Character
import io.reactivex.Single

class CharactersNetworkDataSource(private val charactersApiService: CharactersApiService) {

    fun loadCharacters(pageNumber: Int): Single<List<Character>>{
        return charactersApiService.getCharacters(pageNumber)
            .map(ResponseToCharactersMapper())
    }
}