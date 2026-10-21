package com.example.byd_sushi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.byd_sushi.databinding.ActivityMainBinding
import com.example.byd_sushi.home.pertemuan_3.ThirdActivity
import com.example.byd_sushi.home.pertemuan_4.FourthActivity
import com.example.byd_sushi.home.pertemuan_5.FifthActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMyProject.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }
        binding.btnDashboard.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        binding.btnKirimKode.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }
        binding.btnToFifth.setOnClickListener {
            startActivity(Intent(this, FifthActivity::class.java))
        }
    }
}