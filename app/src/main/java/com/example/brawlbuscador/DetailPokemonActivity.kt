package com.example.brawlbuscador

import android.os.Bundle
import android.util.TypedValue
import android.view.View
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
import kotlin.math.roundToInt

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
        Picasso.get().load(pokemon.sprites.frontDefault).into(binding.ivPokemon)
        Picasso.get().load(pokemon.sprites.frontDefault).into(binding.ivPokemonFront)
        Picasso.get().load(pokemon.sprites.backDefault).into(binding.ivPokemonBack)
        Picasso.get().load(pokemon.sprites.frontShiny).into(binding.ivPokemonFrontShiny)
        Picasso.get().load(pokemon.sprites.backShiny).into(binding.ivPokemonBackShiny)
        prepareStats(pokemon.stats)
        binding.tvPokemonName.text = pokemon.name.replaceFirstChar { it.uppercase() }
        binding.tvPokemonId.text = "Nº Pokedex: ${pokemon.id}"
        binding.tvTypes.text = "Types: ${pokemon.types.joinToString { it.type.name.capitalize() }}"
        binding.tvAb1.text = "${pokemon.abilities.joinToString { it.ability.name.capitalize() }}"
    }


    private fun prepareStats(stats: List<PokemonStatResponse>) {
        for (statResponse in stats) {
            val baseStat = statResponse.baseStat
            when (statResponse.stat.name) {
                "hp" -> updateHeight(binding.viewHP, baseStat.toString())
                "attack" -> updateHeight(binding.viewAttack, baseStat.toString())
                "defense" -> updateHeight(binding.viewDefense, baseStat.toString())
                "special-attack" -> updateHeight(binding.viewAttackS, baseStat.toString())
                "special-defense" -> updateHeight(binding.viewDefenseS, baseStat.toString())
                "speed" -> updateHeight(binding.viewSpeed, baseStat.toString())
            }
        }
    }


    private fun updateHeight(view: View, stat:String) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px:Float): Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
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
