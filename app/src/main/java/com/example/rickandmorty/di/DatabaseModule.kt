package com.example.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.database.AppDatabase
import com.example.rickandmorty.data.database.CharactersLocalDataSource
import com.example.rickandmorty.data.database.dao.CharacterBriefDao
import com.example.rickandmorty.data.database.dao.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase::class.toString())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Reusable
    fun provideCharacterBriefDao(database: AppDatabase): CharacterBriefDao{
        return database.characterBriefDao()
    }

    @Provides
    @Reusable
    fun provideCharacterDao(database: AppDatabase): CharacterDao{
        return database.characterDao()
    }

    @Provides
    @Reusable
    fun provideCharactersLocalDataSource(
        characterBriefDao: CharacterBriefDao,
        characterDao: CharacterDao
    ): CharactersLocalDataSource{
        return CharactersLocalDataSource(characterBriefDao, characterDao)
    }
}