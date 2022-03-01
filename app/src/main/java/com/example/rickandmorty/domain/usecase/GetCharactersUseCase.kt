package com.example.rickandmorty.domain.usecase

import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.domain.repository.CharactersRepository
import io.reactivex.Observable

interface GetCharactersUseCase : (Int) -> Observable<List<Character>>

class GetCharactersUseCaseImpl(private val charactersRepository: CharactersRepository) : GetCharactersUseCase {

    override fun invoke(pageNumber: Int): Observable<List<Character>> {
        return charactersRepository.getCharacters(pageNumber)
    }
}