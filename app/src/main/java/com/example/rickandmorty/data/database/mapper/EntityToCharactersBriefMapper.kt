package com.example.rickandmorty.data.database.mapper

import com.example.rickandmorty.data.database.entity.CharacterBriefEntity
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.domain.entity.Location

class EntityToCharactersBriefMapper : (List<CharacterBriefEntity>) -> List<Character> {
    override fun invoke(entityList: List<CharacterBriefEntity>): List<Character> {
        return entityList.map {
            Character(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl,
                status = DEFAULT_STATUS,
                species = DEFAULT_SPECIES,
                location = Location(DEFAULT_LOCATION_NAME),
                episodes = emptyList()
            )
        }
    }

    companion object{
        const val DEFAULT_STATUS = ""
        const val DEFAULT_SPECIES = ""
        const val DEFAULT_LOCATION_NAME = ""
    }
}

class CharactersToEntityBriefMapper : (List<Character>) -> List<CharacterBriefEntity>{
    override fun invoke(characters: List<Character>): List<CharacterBriefEntity> {
        return characters.map {
            CharacterBriefEntity(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl
            )
        }
    }

}