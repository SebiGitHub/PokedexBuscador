package com.example.brawlbuscador

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlbuscador.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    fun bind(pokemon: PokemonData, onItemSelected: (Int) -> Unit) {
        binding.tvPokemonName.text = pokemon.name.replaceFirstChar { it.uppercase() }

        Picasso.get()
            .load(pokemon.sprite)
            .error(R.drawable.default_pokemon_image) // Imagen de respaldo en caso de error
            .into(binding.ivPokemon)

        binding.root.setOnClickListener { onItemSelected(pokemon.id) }
    }
}
