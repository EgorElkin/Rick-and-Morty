package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.entity.Character
import io.reactivex.Observable

interface CharactersRepository {

    fun getCharacters(pageNumber: Int): Observable<List<Character>>

    fun getDetails(characterId: Int): Observable<Character>
}