package com.example.brawlbuscador

import com.google.gson.annotations.SerializedName


data class PokemonData(
    val name: String,
    val sprite: String,
    val id: Int
)


data class PokemonListResponse(
    @SerializedName("results") val results: List<PokemonNameResponse>
)

data class PokemonNameResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
