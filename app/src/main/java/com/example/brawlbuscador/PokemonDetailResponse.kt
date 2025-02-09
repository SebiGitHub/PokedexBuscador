package com.example.brawlbuscador

import com.google.gson.annotations.SerializedName

// Respuesta detallada de un Pokémon
data class PokemonDetailResponse(
    @SerializedName("name") val name: String,                       // Nombre del Pokémon
    @SerializedName("id") val id: Int,                               // ID del Pokémon
    @SerializedName("sprites") val sprites: PokemonSpritesResponse,  // Imágenes del Pokémon
    @SerializedName("types") val types: List<PokemonDetailTypeResponse>,   // Tipos del Pokémon
    @SerializedName("stats") val stats: List<PokemonStatResponse>,    // Estadísticas del Pokémon
    @SerializedName("abilities") val abilities: List<PokemonAbilitiesResponse>    // Estadísticas del Pokémon
)

data class PokemonAbilitiesResponse (
    @SerializedName("ability") val ability: PokemonDetailAbilityDetail     // Información sobre las habilidades
)

data class PokemonDetailAbilityDetail (
    @SerializedName("name") val name: String    // Nombre del tipo (ej. "fire", "water")
)


// Respuesta para las imágenes (sprites)
data class PokemonSpritesResponse(
    @SerializedName("front_default") val frontDefault: String,        // Imagen principal del Pokémon
    @SerializedName("back_default") val backDefault: String,
    @SerializedName("front_shiny") val frontShiny: String,
    @SerializedName("back_shiny") val backShiny: String,

)

// Respuesta de los tipos del Pokémon
data class PokemonDetailTypeResponse(
    @SerializedName("type") val type: PokemonDetailTypeDetail            // Información sobre el tipo
)

// Detalles del tipo (fuego, agua, etc.)
data class PokemonDetailTypeDetail(
    @SerializedName("name") val name: String                       // Nombre del tipo (ej. "fire", "water")
)

// Respuesta para las estadísticas del Pokémon (como HP, ataque, etc.)
data class PokemonStatResponse(
    @SerializedName("base_stat") val baseStat: Int,  // Valor de la estadística
    @SerializedName("stat") val stat: PokemonStatInfoResponse // Información de la estadística
)

// Información sobre cada estadística (ej. "attack", "defense")
data class PokemonStatInfoResponse(
    @SerializedName("name") val name: String,  // Nombre de la estadística (ej. "attack", "defense")
)

