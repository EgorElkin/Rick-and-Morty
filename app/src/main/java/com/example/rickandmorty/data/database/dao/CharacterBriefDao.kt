package com.example.rickandmorty.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.database.entity.CharacterBriefEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CharacterBriefDao {

    @Query("SELECT * FROM CHARACTER_BRIEF_TABLE")
    fun getAll(): Single<List<CharacterBriefEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(characters: List<CharacterBriefEntity>): Completable

    @Query("DELETE FROM CHARACTER_BRIEF_TABLE")
    fun deleteAll(): Completable
}