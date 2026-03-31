package hr.unidu.kz.primjerliste

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hr.unidu.kz.primjerliste.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.najjednostavnijiMenu.setOnClickListener {

            val intent = Intent(this, NajjednostavnijaListaActivity::class.java)
            startActivity(intent)
        }

        binding.jednostavnaMenu.setOnClickListener {

            val intent = Intent(this, JednostavnaListaActivity::class.java)
            startActivity(intent)
        }

        binding.slozenaMenu.setOnClickListener {
            val intent = Intent(this, SlozenaListaActivity::class.java)
            startActivity(intent)
        }



    }
}