package com.example.rickandmorty.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.database.entity.CharacterEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CHARACTER_TABLE WHERE id = :characterId")
    fun getCharacter(characterId: Int): Single<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterEntity): Completable
}