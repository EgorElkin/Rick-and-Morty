package com.example.rickandmorty.presentation.characters.adapter.item

import com.example.rickandmorty.domain.entity.Character

class CharactersToItemsMapper : (List<Character>) -> List<CharacterItem>{

    override fun invoke(characters: List<Character>): List<CharacterItem> {
        return characters.map {
            CharacterItem(
                id = it.id,
                imageUrl = it.imageUrl,
                name = it.name
            )
        }
    }
}