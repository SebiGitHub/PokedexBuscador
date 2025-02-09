package com.example.brawlbuscador

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int): Response<PokemonListResponse>

    //Detail
    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") pokemonId:String): Response<PokemonDetailResponse>

}
