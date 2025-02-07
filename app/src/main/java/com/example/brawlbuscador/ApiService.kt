package com.example.brawlbuscador

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") pokemonName: String): Response<PokemonDataResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") pokemonId:String): Response<PokemonDetailResponse>

    @GET("pokemon?limit=1000") // Ajusta el límite según la API
    suspend fun getAllPokemon(): Response<PokemonList>

}
