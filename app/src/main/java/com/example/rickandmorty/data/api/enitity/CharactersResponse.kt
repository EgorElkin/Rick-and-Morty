package com.example.rickandmorty.data.api.enitity

import com.google.gson.annotations.SerializedName

class CharactersResponse(
    @SerializedName(value = "info")
    val responseInfo: ResponseInfo,
    @SerializedName(value = "results")
    val results: List<DetailsResponse>
)

class ResponseInfo(
    @SerializedName(value = "count")
    val charactersCount: Int,
    @SerializedName(value = "pages")
    val pagesCount: Int,
    @SerializedName(value = "next")
    val nextPageUrl: String,
    @SerializedName(value = "prev")
    val prevPageUrl: String,
)