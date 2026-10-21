package com.example.byd_sushi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.byd_sushi.databinding.ActivityMainBinding
import com.example.byd_sushi.pertemuan_3.ThirdActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMyProject.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }

        binding.btnKirimKode.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }
    }
}