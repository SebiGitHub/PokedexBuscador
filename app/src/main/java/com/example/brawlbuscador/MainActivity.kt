package com.example.brawlbuscador


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brawlbuscador.DetailPokemonActivity.Companion.EXTRA_ID
import com.example.brawlbuscador.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        // Configuración de la barra de búsqueda
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    binding.progressBar.isVisible = false
                    adapter.updateList(emptyList())  // Limpiar lista si no hay texto
                }
                return false
            }
        })

        // Inicializar el adaptador y RecyclerView
        adapter = PokemonAdapter { pokemonId -> navigateToDetail(pokemonId.toString()) }
        binding.rvbrawl.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun searchByName(query: String) {
        if (query.isEmpty()) return // No hacer la búsqueda si el campo está vacío
        binding.progressBar.isVisible = true

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val myResponse: Response<PokemonDataResponse> =
                    retrofit.create(ApiService::class.java).getPokemon(query)

                if (myResponse.isSuccessful) {
                    myResponse.body()?.let { response ->
                        // Aquí actualizamos con la lista completa de Pokémon, no solo tipos
                        withContext(Dispatchers.Main) {
                            adapter.updateList(listOf(response))  // Pasar Pokémon completo a la lista
                            binding.progressBar.isVisible = false
                        }
                    } ?: run {
                        Log.e("MainActivity", "Response body is null")
                        withContext(Dispatchers.Main) {
                            binding.progressBar.isVisible = false
                        }
                    }
                } else {
                    Log.e("MainActivity", "Failed response: ${myResponse.code()}")
                    withContext(Dispatchers.Main) {
                        binding.progressBar.isVisible = false
                    }
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Error during API call", e)
                withContext(Dispatchers.Main) {
                    binding.progressBar.isVisible = false
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/") // Base URL de la API Pokémon
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailPokemonActivity::class.java)
        intent.putExtra(DetailPokemonActivity.EXTRA_ID, id)
        startActivity(intent)
    }
}

