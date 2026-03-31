package hr.unidu.kz.primjerliste

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.unidu.kz.primjerliste.databinding.ActivityNajjednostavnijaListaBinding
import hr.unidu.kz.primjerliste.databinding.ItemRedakBinding

class NajjednostavnijaListaActivity : AppCompatActivity(){
    private lateinit var binding: ActivityNajjednostavnijaListaBinding
    private lateinit var mojAdapter: NajjednostavnijaListaAdapter
    private lateinit var lista: List<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNajjednostavnijaListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView

        // 1. Postavi LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 2. Dodaj podatke u adapter i spoji ga s RV
        ucitajPodatke()
        mojAdapter = NajjednostavnijaListaAdapter(lista)
        recyclerView.adapter = mojAdapter
    }

    fun ucitajPodatke() {

        lista = listOf(
            "Abra", "Absol", "Alakazam", "Arbok", "Arcanine", "Articuno",
            "Bagon", "Bayleef", "Beedrill", "Bellossom", "Bellsprout",
            "Blastoise", "Blaziken", "Breloom", "Bulbasaur", "Buneary",
            "Butterfree", "Cacnea", "Cacturne", "Camerupt", "Caterpie",
            "Celebi", "Charizard", "Charmander", "Charmeleon"
        )
    }
}


class NajjednostavnijaListaAdapter(
    private val lista: List<String>,
 ) : RecyclerView.Adapter<NajjednostavnijaListaAdapter.NajjednostavnijaViewHolder>() {

    // Klasa koja "drži" reference na UI elemente jednog retka - prima čitav binding objekt
    inner class NajjednostavnijaViewHolder(val binding: ItemRedakBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NajjednostavnijaViewHolder {
        // Priprema layouta
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRedakBinding.inflate(layoutInflater, parent, false)
        return NajjednostavnijaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NajjednostavnijaViewHolder, position: Int) {
        val pok = lista[position]
        holder.binding.tvPokemonIme.text = pok
    }


    override fun getItemCount() = lista.size
}