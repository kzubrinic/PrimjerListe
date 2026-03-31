package hr.unidu.kz.primjerliste

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import hr.unidu.kz.primjerliste.databinding.ActivitySlozenaListaBinding
import hr.unidu.kz.primjerliste.databinding.ItemPokemonBinding

class SlozenaListaActivity : AppCompatActivity(){
    private lateinit var binding: ActivitySlozenaListaBinding
    private lateinit var mojAdapter: SlozenaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySlozenaListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView

        // 1. Postavi LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)

        binding.pokIme.setText("Lista pokemona")
        binding.pokIme.isFocusable = false
        binding.pokIme.isClickable = false

        // 2. Dodaj podatke u adapter i spoji ga s RV
        mojAdapter = SlozenaAdapter(ucitajPodatke()){ izabrani ->
            // Akcija koja se odrađuje kada se izabere redak liste
            val jsonString = Json.encodeToString(izabrani)
            val intent = Intent(this, DetaljActivity::class.java)
            intent.putExtra("POKEMON", jsonString)
            startActivity(intent)
        }
        recyclerView.adapter = mojAdapter
    }

    fun ucitajPodatke(): List<Pokemon> {

        return mutableListOf(
            Pokemon("Abra", "Psihički", R.drawable.abra),
            Pokemon("Absol", "Mračni", R.drawable.absol),
            Pokemon("Alakazam", "Psihički", R.drawable.alakazam),
            Pokemon("Arbok", "Otrovni", R.drawable.arbok),
            Pokemon("Arcanine", "Vatreni", R.drawable.arcanine),
            Pokemon("Articuno", "Ledeni/Leteći", R.drawable.articuno),
            Pokemon("Bagon", "Zmajski", R.drawable.bagon),
            Pokemon("Bayleef", "Travni", R.drawable.bayleef),
            Pokemon("Beedrill", "Kukac/Otrovni", R.drawable.beedrill),
            Pokemon("Bellossom", "Travni", R.drawable.bellossom),
            Pokemon("Bellsprout", "Travni/Otrovni", R.drawable.bellsprout),
            Pokemon("Blastoise", "Vodeni", R.drawable.blastoise),
            Pokemon("Blaziken", "Vatreni/Borbeni", R.drawable.blaziken),
            Pokemon("Breloom", "Travni/Borbeni", R.drawable.breloom),
            Pokemon("Bulbasaur", "Travni/Otrovni", R.drawable.bulbasaur),
            Pokemon("Buneary", "Normalni", R.drawable.buneary),
            Pokemon("Butterfree", "Kukac/Leteći", R.drawable.butterfree),
            Pokemon("Cacnea", "Travni", R.drawable.cacnea),
            Pokemon("Cacturne", "Travni/Mračni", R.drawable.cacturne),
            Pokemon("Camerupt", "Vatreni/Zemljani", R.drawable.camerupt),
            Pokemon("Caterpie", "Kukac", R.drawable.caterpie),
            Pokemon("Celebi", "Psihički/Travni", R.drawable.celebi),
            Pokemon("Charizard", "Vatreni/Leteći", R.drawable.charizard),
            Pokemon("Charmander", "Vatreni", R.drawable.charmander),
            Pokemon("Charmeleon", "Vatreni", R.drawable.charmeleon)
        )
    }
}

@Serializable
data class Pokemon(val ime: String, val tip: String, val slika: Int)

class SlozenaAdapter(
    private val lista: List<Pokemon>,
    private val onItemClick: (Pokemon) -> Unit // Lambda funkcija za klik
) :
    RecyclerView.Adapter<SlozenaAdapter.SlozenaViewHolder>() {

    // Klasa koja "drži" reference na UI elemente jednog retka - prima čitav binding objekt
    class SlozenaViewHolder(val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlozenaViewHolder {
        // Napuhujemo tvoj novi layout
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPokemonBinding.inflate(layoutInflater, parent, false)
        return SlozenaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlozenaViewHolder, position: Int) {
        val pok = lista[position]
        holder.binding.tvNaziv.text = pok.ime
        holder.binding.tvTip.text = pok.tip
        holder.binding.imgPokemon.setImageResource(pok.slika)
        // Hvatanje klika na čitav redak
        holder.binding.root.setOnClickListener {
            onItemClick(pok)
            android.util.Log.d("MOJ_TEST", "Kliknuto na: $pok")
        }

    }


    override fun getItemCount() = lista.size
}