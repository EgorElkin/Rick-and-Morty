package com.example.rickandmorty.domain.usecase

import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.domain.repository.CharactersRepository
import io.reactivex.Observable

interface GetDetailsUseCase : (Int) -> Observable<Character>

class GetDetailsUseCaseImpl(private val charactersRepository: CharactersRepository) : GetDetailsUseCase {

    override fun invoke(characterId: Int): Observable<Character> {
        return charactersRepository.getDetails(characterId)
    }
}