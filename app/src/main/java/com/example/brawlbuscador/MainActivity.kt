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
                    adapter.updateListName(emptyList())
                    adapter.updateListSprites(emptyList())
                    adapter.updateListId(emptyList()) // Limpiar lista si no hay texto
                }
                return false
            }
        })

        // Inicializar el adaptador y RecyclerView
        adapter = PokemonAdapter { pokemonId -> navigateToDetail(pokemonId.toString()) }
        binding.rvpokemon.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {

            val myResponseName: Response<PokemonsListNames> =
                retrofit.create(ApiService::class.java).getPokemonName(query)

            if (myResponseName.isSuccessful) {
                Log.i("aristidevs", "funciona :)")

                val responseName: PokemonsListNames? = myResponseName.body()

                if (responseName != null) {
                    Log.i("aristidevs", responseName.toString())
                    runOnUiThread {
                        adapter.updateListName(responseName.species)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("aristidevs", "No funciona :(")
            }

            val myResponseSprites: Response<PokemonsListSprites> =
                retrofit.create(ApiService::class.java).getPokemonSprites(query)

            if (myResponseSprites.isSuccessful) {
                Log.i("aristidevs", "funciona :)")

                val responseSprites: PokemonsListSprites? = myResponseSprites.body()

                if (responseSprites != null) {
                    Log.i("aristidevs", responseSprites.toString())
                    runOnUiThread {
                        adapter.updateListSprites(responseSprites.sprites)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("aristidevs", "No funciona :(")
            }

            val myResponseId: Response<PokemonId> =
                retrofit.create(ApiService::class.java).getPokemonId(query)


            if (myResponseSprites.isSuccessful) {
                Log.i("aristidevs", "funciona :)")

                val responseId: PokemonId? = myResponseId.body()

                if (responseId != null) {
                    Log.i("aristidevs", responseId.toString())
                    runOnUiThread {
                        adapter.updateListId(responseId.id)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {
                Log.i("aristidevs", "No funciona :(")
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