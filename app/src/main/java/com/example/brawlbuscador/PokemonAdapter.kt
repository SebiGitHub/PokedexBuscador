package com.example.brawlbuscador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PokemonAdapter(
    private var pokemonListName: List<PokemonNames> = emptyList(),
    private var pokemonListSprites: List<PokemonSprites> = emptyList(),
    private var pokemonListId: List<PokemonId> = emptyList(),
    private val onItemSelected: (String) -> Unit
) : RecyclerView.Adapter<PokemonViewHolder>() {

    // Actualiza la lista de Pokémon
    fun updateListName(newPokemonListName: List<PokemonNames>) {
        this.pokemonListName = newPokemonListName
        notifyDataSetChanged()
    }

    fun updateListSprites(pokemonListSprites: List<PokemonSprites>) {
        this.pokemonListSprites = pokemonListSprites
        notifyDataSetChanged()
    }

    fun updateListId(pokemonListId: List<PokemonId>) {
        this.pokemonListId = pokemonListId
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: PokemonViewHolder, position: Int) {
        viewHolder.bindName(pokemonListName[position])
        viewHolder.bindSprites(pokemonListSprites[position])
        viewHolder.bindId(pokemonListId[position], onItemSelected)
    }

    override fun getItemCount() = pokemonListName.size
}
