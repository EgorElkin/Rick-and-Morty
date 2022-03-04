package com.example.rickandmorty.data.database.mapper

import androidx.room.TypeConverter
import com.example.rickandmorty.data.database.entity.CharacterEntity
import com.example.rickandmorty.domain.entity.Character
import com.example.rickandmorty.domain.entity.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EntityToDetailsMapper : (CharacterEntity) -> Character {

    override fun invoke(entity: CharacterEntity): Character {
        return entity.run {
            Character(
                id = id,
                name = name,
                species = species,
                status = status,
                location = location,
                imageUrl = imageUrl,
                episodes = episodes
            )
        }
    }
}

class DetailsToEntityMapper : (Character) -> CharacterEntity{

    override fun invoke(details: Character): CharacterEntity {
        return details.run {
            CharacterEntity(
                id = id,
                name = name,
                status = status,
                species = species,
                location = location,
                imageUrl = imageUrl,
                episodes = episodes
            )
        }
    }
}

class LocationConverter{

    @TypeConverter
    fun fromLocation(location: Location): String{
        return location.name
    }

    @TypeConverter
    fun toLocation(name: String): Location{
        return Location(name)
    }
}

class EpisodesConverter{

    private val gson = Gson()
    private val type = object : TypeToken<List<String>>(){}.type

    @TypeConverter
    fun fromEpisodes(episodes: List<String>): String{
        return gson.toJson(episodes, type)
    }

    @TypeConverter
    fun toEpisodes(json: String): List<String>{
        return gson.fromJson(json, type)
    }
}