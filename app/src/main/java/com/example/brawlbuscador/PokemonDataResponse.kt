package com.example.brawlbuscador

import com.google.gson.annotations.SerializedName

// Respuesta básica al buscar un Pokémon
data class PokemonDataResponse(
    @SerializedName("types") val types: List<PokemonTypeResponse>,  // Tipos del Pokémon
)

data class PokemonList(
    @SerializedName("pokemon") val pokemon: List<PokemonDataResponse> = emptyList() // Lista vacía en caso de `null`
)


//Id
data class PokemonId(
    @SerializedName("id") val id: List<PokemonId> = emptyList(),  // ID único del Pokémon
    @SerializedName("id") val id1: String  // ID único del Pokémon
)


//Sprite
data class PokemonsListSprites(
    @SerializedName("sprites") val sprites: List<PokemonSprites>       // Imagen del Pokémon)
)

data class PokemonSprites(
    @SerializedName("front_default") val frontDefault: String // Imagen frontal del Pokémon
)


//Name
data class PokemonsListNames(
    @SerializedName("species") val species: List<PokemonNames> // Lista de Pokémon
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
