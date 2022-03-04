package com.example.rickandmorty.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.domain.entity.Location

@Entity(tableName = "character_table")
data class CharacterEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "species")
    val species: String,
    @ColumnInfo(name = "location")
    val location: Location,
    @ColumnInfo(name = "img_url")
    val imageUrl: String,
    @ColumnInfo(name = "episodes")
    val episodes: List<String>
)