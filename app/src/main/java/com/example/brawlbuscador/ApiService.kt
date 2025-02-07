package com.example.brawlbuscador

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") pokemonName: String): Response<PokemonDataResponse>

    @GET("/api/10229233666327556/{id}")
    suspend fun getPokemonDetail(@Path("id") superheroId:String):Response<PokemonDetailResponse>
}
