package com.example.rickandmorty.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.database.dao.CharacterBriefDao
import com.example.rickandmorty.data.database.dao.CharacterDao
import com.example.rickandmorty.data.database.entity.CharacterBriefEntity
import com.example.rickandmorty.data.database.entity.CharacterEntity
import com.example.rickandmorty.data.database.mapper.EpisodesConverter
import com.example.rickandmorty.data.database.mapper.LocationConverter

@Database(entities = [CharacterBriefEntity::class, CharacterEntity::class], version = 2, exportSchema = false)
@TypeConverters(LocationConverter::class, EpisodesConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterBriefDao(): CharacterBriefDao

    abstract fun characterDao(): CharacterDao
}