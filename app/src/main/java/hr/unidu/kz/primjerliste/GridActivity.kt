package hr.unidu.kz.primjerliste

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.unidu.kz.primjerliste.databinding.ActivityGridBinding
import hr.unidu.kz.primjerliste.databinding.ItemGridBinding
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GridActivity : AppCompatActivity(){
    private lateinit var binding:
    ActivityGridBinding
    private lateinit var mojAdapter: GridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGridBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView

        val brstupaca = intent.getIntExtra("BROJ_STUPACA", 2)


        // 1. Postavi LayoutManager
        if (intent.getBooleanExtra("ORIJENTACIJA", false)) {
            // Klasična "okomita orijentacija"
            recyclerView.layoutManager = GridLayoutManager(this, brstupaca)
        }else {
            // vodoravno
            recyclerView.layoutManager = GridLayoutManager(this,
                brstupaca,
                RecyclerView.HORIZONTAL,
                false)
        }

        // Horizontalni grid
        //

        binding.pokIme.setText("Lista pokemona")
        binding.pokIme.isFocusable = false
        binding.pokIme.isClickable = false

        // 2. Dodaj podatke u adapter i spoji ga s RV
        mojAdapter = GridAdapter(Pomocna.ucitajPokemone()){ izabrani ->
                // Akcija koja se odrađuje kada se izabere redak liste
                val jsonString = Json.encodeToString(izabrani)
            val intent = Intent(this, DetaljActivity::class.java)
            intent.putExtra("POKEMON", jsonString)
            startActivity(intent)
        }
        recyclerView.adapter = mojAdapter
    }


}

class GridAdapter(
        private val lista: List<Pokemon>,
        private val onItemClick: (Pokemon) -> Unit // Lambda funkcija za klik
) :
        RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

// Klasa koja "drži" reference na UI elemente jednog retka - prima čitav binding objekt
class GridViewHolder(val binding: ItemGridBinding) :
        RecyclerView.ViewHolder(binding.root)


override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
    // Napuhujemo tvoj novi layout
    val layoutInflater = LayoutInflater.from(parent.context)
    val binding = ItemGridBinding.inflate(layoutInflater, parent, false)
    return GridViewHolder(binding)
}

override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
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