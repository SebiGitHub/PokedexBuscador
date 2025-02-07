package com.example.brawlbuscador

import com.google.gson.annotations.SerializedName

// Respuesta detallada de un Pokémon
data class PokemonDetailResponse(
    @SerializedName("name") val name: String,                       // Nombre del Pokémon
    @SerializedName("id") val id: Int,                               // ID del Pokémon
    @SerializedName("sprites") val sprites: PokemonSpritesResponse,  // Imágenes del Pokémon
    @SerializedName("types") val types: List<PokemonDetailTypeResponse>,   // Tipos del Pokémon
    @SerializedName("stats") val stats: List<PokemonStatResponse>    // Estadísticas del Pokémon
)

// Respuesta para las imágenes (sprites)
data class PokemonSpritesResponse(
    @SerializedName("front_default") val frontDefault: String        // Imagen principal del Pokémon
)

// Respuesta de los tipos del Pokémon (fuego, agua, etc.)
data class PokemonDetailTypeResponse(
    @SerializedName("type") val type: PokemonDetailTypeDetail            // Información sobre el tipo
)

// Detalles del tipo (fuego, agua, etc.)
data class PokemonDetailTypeDetail(
    @SerializedName("name") val name: String                       // Nombre del tipo (ej. "fire", "water")
)

// Respuesta para las estadísticas del Pokémon (como HP, ataque, etc.)
data class PokemonStatResponse(
    @SerializedName("stat") val stat: PokemonStatInfoResponse,     // Información de la estadística
    @SerializedName("base_stat") val baseStat: Int                  // Valor de la estadística
)

// Información sobre cada estadística (ej. "attack", "defense", "speed")
data class PokemonStatInfoResponse(
    @SerializedName("name") val name: String                       // Nombre de la estadística (ej. "attack", "defense")
)

// Detalles adicionales del Pokémon, como su biografía
data class PokemonBiography(
    @SerializedName("full-name") val fullName: String,             // Nombre completo del Pokémon
    @SerializedName("publisher") val publisher: String             // Publisher o entidad que lo creó
)
