package com.example.rickandmorty.data.database

import com.example.rickandmorty.data.database.dao.CharacterBriefDao
import com.example.rickandmorty.data.database.dao.CharacterDao
import com.example.rickandmorty.data.database.mapper.CharactersToEntityBriefMapper
import com.example.rickandmorty.data.database.mapper.DetailsToEntityMapper
import com.example.rickandmorty.data.database.mapper.EntityToCharactersBriefMapper
import com.example.rickandmorty.data.database.mapper.EntityToDetailsMapper
import com.example.rickandmorty.domain.entity.Character
import io.reactivex.Completable
import io.reactivex.Single

class CharactersLocalDataSource(
    private val characterBriefDao: CharacterBriefDao,
    private val detailsDao: CharacterDao
) {

    fun getAllCharactersBrief(): Single<List<Character>> = characterBriefDao.getAll().map(EntityToCharactersBriefMapper())

    fun deleteAllCharactersBrief(): Completable = characterBriefDao.deleteAll()

    fun addAllCharactersBrief(characters: List<Character>): Completable = characterBriefDao.insertAll(CharactersToEntityBriefMapper().invoke(characters))

    fun getDetails(characterId: Int): Single<Character> = detailsDao.getCharacter(characterId).map(EntityToDetailsMapper())

    fun addDetails(character: Character): Completable = detailsDao.insertCharacter(DetailsToEntityMapper().invoke(character))
}