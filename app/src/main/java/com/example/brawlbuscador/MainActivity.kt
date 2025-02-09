package com.example.brawlbuscador

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brawlbuscador.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchByName(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    binding.progressBar.isVisible = false
                    adapter.updateList(emptyList())
                }
                return false
            }
        })

        adapter = PokemonAdapter(emptyList()) { pokemonId -> navigateToDetail(pokemonId.toString()) }
        binding.rvpokemon.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofit.create(ApiService::class.java).getPokemonList(1000) // Obtener los primeros 1000 Pokémon

                if (response.isSuccessful) {
                    val pokemonList = response.body()?.results ?: emptyList()

                    // Filtrar solo los Pokémon que comiencen con la consulta del usuario
                    val filteredPokemon = pokemonList.filter { it.name.startsWith(query, ignoreCase = true) }

                    // Convertir los Pokémon filtrados en datos con ID e imagen
                    val pokemonDataList = filteredPokemon.map { pokemon ->
                        val detailResponse = retrofit.create(ApiService::class.java).getPokemonDetail(pokemon.name).body()

                        PokemonData(
                            name = pokemon.name,
                            sprite = detailResponse?.sprites?.frontDefault ?: "", // Evitar valores nulos
                            id = detailResponse?.id ?: 0
                        )
                    }

                    runOnUiThread {
                        adapter.updateList(pokemonDataList)
                        binding.progressBar.isVisible = false
                    }
                } else {
                    Log.e("MainActivity", "Error en la respuesta de la API: ${response.code()}")
                    runOnUiThread { binding.progressBar.isVisible = false }
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error en la búsqueda de Pokémon", e)
                runOnUiThread { binding.progressBar.isVisible = false }
            }
        }
    }



    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailPokemonActivity::class.java)
        intent.putExtra(DetailPokemonActivity.EXTRA_ID, id)
        startActivity(intent)
    }
}
