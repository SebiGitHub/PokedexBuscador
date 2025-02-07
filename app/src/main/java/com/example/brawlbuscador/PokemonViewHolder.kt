package com.example.brawlbuscador

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlbuscador.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso

class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    fun bind(pokemon: PokemonDataResponse, onItemSelected: (Int) -> Unit) {
        // Capitaliza el nombre del Pokémon
        binding.tvPokemonName.text = pokemon.name.replaceFirstChar { it.uppercase() }

        // Carga la imagen con Picasso y maneja los errores
        Picasso.get()
            .load(pokemon.sprites.frontDefault)
            .error(R.drawable.default_pokemon_image)  // Imagen en caso de error
            .into(binding.ivPokemon)

        // Configura el listener para el clic
        binding.root.setOnClickListener { onItemSelected(pokemon.id) }
    }
}

