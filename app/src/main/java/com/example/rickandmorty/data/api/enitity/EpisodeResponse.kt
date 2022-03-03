package com.example.rickandmorty.data.api.enitity

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "air_date")
    val airDate: String
)