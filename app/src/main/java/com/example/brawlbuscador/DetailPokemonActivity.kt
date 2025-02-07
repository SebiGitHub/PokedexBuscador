package com.example.brawlbuscador

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.brawlbuscador.databinding.ActivityDetailPokemonBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailPokemonActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id: String = intent.getStringExtra(EXTRA_ID) ?: "default_id"
        getPokemonInformation(id)
    }

    private fun getPokemonInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = getRetrofit().create(ApiService::class.java).getPokemonDetail(id)

                if (response.isSuccessful && response.body() != null) {
                    withContext(Dispatchers.Main) { createUI(response.body()!!) }
                } else {
                    withContext(Dispatchers.Main) { showError() }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) { showError() }
            }
        }
    }

    private fun createUI(pokemon: PokemonDetailResponse) {
        Picasso.get()
            .load(pokemon.sprites.frontDefault)

        binding.tvPokemonName.text = pokemon.name.replaceFirstChar { it.uppercase() }
        binding.tvPokemonId.text = "ID: ${pokemon.id}"
        binding.tvTypes.text = "Types: ${pokemon.types.joinToString { it.type.name.capitalize() }}"
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun showError() {
        // Mostrar algún tipo de mensaje de error
        binding.tvTypes.text = "Error loading data"
    }
}
