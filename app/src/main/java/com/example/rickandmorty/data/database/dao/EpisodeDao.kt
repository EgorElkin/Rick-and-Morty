package com.example.rickandmorty.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.database.entity.EpisodeEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM EPISODE_TABLE WHERE id IN (:ids)")
    fun getEpisodes(ids: List<Int>): Single<List<EpisodeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEpisodes(episodes: List<EpisodeEntity>): Completable
}