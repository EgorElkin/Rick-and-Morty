package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.api.enitity.CharactersResponse
import com.example.rickandmorty.data.api.enitity.DetailsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApiService {

    @GET(value = "character")
    fun getCharacters(@Query(value = "page") pageNumber: Int): Single<CharactersResponse>

    @GET(value = "character/{character_id}")
    fun getDetails(@Path(value = "character_id") characterId: Int): Single<DetailsResponse>
}