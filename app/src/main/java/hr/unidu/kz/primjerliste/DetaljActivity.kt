package hr.unidu.kz.primjerliste

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.unidu.kz.primjerliste.databinding.ActivityDetaljBinding
import kotlinx.serialization.json.Json

class DetaljActivity : AppCompatActivity(){
    private lateinit var binding: ActivityDetaljBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetaljBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonPok = intent.getStringExtra("POKEMON")
        val pok = if (jsonPok != null) {
            Json.decodeFromString<Pokemon>(jsonPok)
        } else {
            Pokemon("Nepoznato", "Nepoznato", R.drawable.pokemonslovo)
        }

        binding.tvNaziv.text = pok.ime
        binding.tvTip.text = pok.tip
        binding.imgPokemon.setImageResource(pok.slika)
    }
}