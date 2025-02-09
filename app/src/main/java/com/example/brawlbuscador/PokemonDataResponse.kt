package com.example.brawlbuscador

import com.google.gson.annotations.SerializedName

// Respuesta básica al buscar un Pokémon
data class PokemonDataResponse(
    @SerializedName("types") val types: List<PokemonTypeResponse>,  // Tipos del Pokémon
)

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



//Id
data class PokemonId(
    @SerializedName("id") val id: Int  // ID único del Pokémon
)


//Sprite
data class PokemonsListSprites(
    @SerializedName("sprites") val sprites: PokemonSprites // Imagen del Pokémon)
)

data class PokemonSprites(
    @SerializedName("front_default") val frontDefault: String // Imagen frontal del Pokémon
)


//Name
data class PokemonsListNames(
    @SerializedName("form") val form: List<PokemonNames> // Lista de Pokémon
)

data class PokemonNames(
    @SerializedName("name") val name: String // Nombre del Pokémon
)



// Respuesta de los tipos del Pokémon (por ejemplo, fuego, agua, etc.)
data class PokemonTypeResponse(
    @SerializedName("type") val type: PokemonTypeDetail           // Detalles sobre el tipo
)

// Detalles del tipo (fuego, agua, etc.)
data class PokemonTypeDetail(
    @SerializedName("name") val name: String                      // Nombre del tipo (ej. "fire", "water")
)
