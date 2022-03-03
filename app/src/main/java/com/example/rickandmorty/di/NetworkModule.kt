package com.example.rickandmorty.di

import com.example.rickandmorty.data.api.CharactersApiService
import com.example.rickandmorty.data.api.CharactersNetworkDataSource
import com.example.rickandmorty.data.api.EpisodesApiService
import com.example.rickandmorty.data.api.EpisodesNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object{
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Reusable
    fun provideCharactersApi(retrofit: Retrofit): CharactersApiService{
        return retrofit.create(CharactersApiService::class.java)
    }

    @Provides
    @Reusable
    fun provideCharactersNetworkDataSource(charactersApiService: CharactersApiService): CharactersNetworkDataSource{
        return CharactersNetworkDataSource(charactersApiService)
    }

    @Provides
    @Reusable
    fun provideEpisodesApi(retrofit: Retrofit): EpisodesApiService{
        return retrofit.create(EpisodesApiService::class.java)
    }

    @Provides
    @Reusable
    fun provideEpisodesNetworkDataSource(episodesApiService: EpisodesApiService): EpisodesNetworkDataSource{
        return EpisodesNetworkDataSource(episodesApiService)
    }
}