package hr.unidu.kz.primjerliste

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.unidu.kz.primjerliste.databinding.ActivityListaBinding
import hr.unidu.kz.primjerliste.databinding.ItemRedakBinding

class JednostavnaListaActivity : AppCompatActivity(){
    private lateinit var binding: ActivityListaBinding
    private lateinit var mojAdapter: JednostavnaAdapter
    private lateinit var lista: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView

        // 1. Postavi LayoutManager
        if (intent.getBooleanExtra("ORIJENTACIJA", false)) {
            // Klasična "okomita orijentacija"
            recyclerView.layoutManager = LinearLayoutManager(this)
        }else {
            // vodoravno
            recyclerView.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        // 2. Dodaj podatke u adapter i spoji ga s RV
        lista = Pomocna.ucitajStringove().toMutableList()
        mojAdapter = JednostavnaAdapter(
            lista,
            onItemClick = { izabrani ->
                binding.pokIme.setText(izabrani)
            },
            onItemLongClick = { izabrani, pozicija ->
                // Brisanje elementa iz liste
                lista.removeAt(pozicija)
                mojAdapter.notifyItemRemoved(pozicija)
            }
        )
        recyclerView.adapter = mojAdapter

        binding.dodaj.setOnClickListener {
            val novi = if (!binding.pokIme.text.toString().isEmpty()) {
                binding.pokIme.text.toString()
            } else {
                "Novi pokemon"
            }
            lista.add(novi) // dodaje se na kraj
            mojAdapter.notifyItemInserted(lista.size - 1)
            // Pozicioniraj s ena dodani element
            recyclerView.scrollToPosition(lista.size - 1)
        }
    }
}


class JednostavnaAdapter(
    private val lista: MutableList<String>,
    private val onItemClick: (String) -> Unit, // Lambda funkcija za klik
    private val onItemLongClick: (String, Int) -> Unit // Lambda funkcija za dug klik
) : RecyclerView.Adapter<JednostavnaAdapter.JednostavnaViewHolder>() {

    // Klasa koja "drži" reference na UI elemente jednog retka - prima čitav binding objekt
    inner class JednostavnaViewHolder(val binding: ItemRedakBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            // Običan klik
            binding.root.setOnClickListener {
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    onItemClick(lista[pos])
                }
            }
            // Dug klik
            binding.root.setOnLongClickListener {
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    onItemLongClick(lista[pos], pos)
                }
                true
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JednostavnaViewHolder {
        // Napuhujemo tvoj novi layout
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRedakBinding.inflate(layoutInflater, parent, false)
        return JednostavnaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JednostavnaViewHolder, position: Int) {
        val pok = lista[position]
        holder.binding.tvPokemonIme.text = pok
    }


    override fun getItemCount() = lista.size
}