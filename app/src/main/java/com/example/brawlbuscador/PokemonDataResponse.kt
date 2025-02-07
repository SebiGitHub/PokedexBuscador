package com.example.brawlbuscador

import com.google.gson.annotations.SerializedName

// Respuesta básica al buscar un Pokémon
data class PokemonDataResponse(
    @SerializedName("id") val id: Int,                            // ID único del Pokémon
    @SerializedName("name") val name: String,                      // Nombre del Pokémon
    @SerializedName("sprites") val sprites: PokemonSprites,        // Imagen del Pokémon
    @SerializedName("types") val types: List<PokemonTypeResponse>  // Tipos del Pokémon
)

// Respuesta para las imágenes del Pokémon (sprites)
data class PokemonSprites(
    @SerializedName("front_default") val frontDefault: String      // Imagen frontal del Pokémon
)

// Respuesta de los tipos del Pokémon (por ejemplo, fuego, agua, etc.)
data class PokemonTypeResponse(
    @SerializedName("type") val type: PokemonTypeDetail           // Detalles sobre el tipo
)

// Detalles del tipo (fuego, agua, etc.)
data class PokemonTypeDetail(
    @SerializedName("name") val name: String                      // Nombre del tipo (ej. "fire", "water")
)
