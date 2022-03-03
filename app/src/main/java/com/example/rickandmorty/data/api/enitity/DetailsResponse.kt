package com.example.rickandmorty.data.api.enitity

import com.google.gson.annotations.SerializedName

data class DetailsResponse(
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "name")
    val name: String,
    @SerializedName(value = "status")
    val status: String,
    @SerializedName(value = "species")
    val species: String,
    @SerializedName(value = "location")
    val location: LocationResponse,
    @SerializedName(value = "image")
    val imageUrl: String,
    @SerializedName(value = "episode")
    val episodes: List<String>
)

data class LocationResponse(
    @SerializedName(value = "name")
    val name: String
)