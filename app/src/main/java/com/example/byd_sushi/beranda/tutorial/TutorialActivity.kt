package com.example.byd_sushi.beranda.tutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.byd_sushi.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup ViewPager dengan adapter
        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )
        binding.tutorialViewPager.adapter = TutorialFragmentAdapter(this, fragmentsList)

        // Hubungkan indikator titik
        binding.dotIndicator.attachTo(binding.tutorialViewPager)

        // Tombol lewati
        binding.btnLewati.setOnClickListener { finish() }
    }
}