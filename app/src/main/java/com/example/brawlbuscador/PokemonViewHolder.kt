package com.example.brawlbuscador

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlbuscador.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    fun bind(pokemon: PokemonDataResponse, ) {




    }

    fun bindName(pokemonName: PokemonNames) {
        // Capitaliza el nombre del Pokémon
        binding.tvPokemonName.text = pokemonName.name

    }

    fun bindSprites(pokemonSprites: PokemonSprites) {
        // Carga la imagen con Picasso y maneja los errores
        Picasso.get()
            .load(pokemonSprites.frontDefault)
            .error(R.drawable.default_pokemon_image)  // Imagen en caso de error
            .into(binding.ivPokemon)
    }

    fun bindId(pokemonId: PokemonId, onItemSelected: (String) -> Unit) {
        // Configura el listener para el clic
        binding.root.setOnClickListener { onItemSelected(pokemonId.id1) }
    }
}

