package com.example.brawlbuscador

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon/{name}")
    suspend fun getPokemonName(@Path("name") pokemonName: String): Response<PokemonsListNames>

    @GET("pokemon/{name}")
    suspend fun getPokemonSprites(@Path("name") pokemonName: String): Response<PokemonsListSprites>

    @GET("pokemon/{name}")
    suspend fun getPokemonId(@Path("name") pokemonName: String): Response<PokemonId>



    //Detail
    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") pokemonId:String): Response<PokemonDetailResponse>

}
