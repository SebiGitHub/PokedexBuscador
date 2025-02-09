package com.example.brawlbuscador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PokemonAdapter(
    private var pokemonList: List<PokemonData> = emptyList(),
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.Adapter<PokemonViewHolder>() {

    fun updateList(newPokemonList: List<PokemonData>) {
        this.pokemonList = newPokemonList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: PokemonViewHolder, position: Int) {
        viewHolder.bind(pokemonList[position], onItemSelected)
    }

    override fun getItemCount() = pokemonList.size
}
