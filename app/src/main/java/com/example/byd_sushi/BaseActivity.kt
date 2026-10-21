package com.example.byd_sushi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.byd_sushi.akun.AkunFragment
import com.example.byd_sushi.beranda.BerandaFragment
import com.example.byd_sushi.data.DataFragment
import com.example.byd_sushi.databinding.ActivityBaseBinding
import com.example.byd_sushi.home.HomeFragment
import com.example.byd_sushi.laporan.LaporanFragment

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // bottom = 0 supaya bottom navigation tidak punya jarak kosong di bawah
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // fragment default, hanya saat pertama dibuka (bukan saat rotasi)
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.beranda -> {
                    replaceFragment(BerandaFragment())
                    true
                }
                R.id.data -> {
                    replaceFragment(DataFragment())
                    true
                }
                R.id.laporan -> {
                    replaceFragment(LaporanFragment())
                    true
                }
                R.id.akun -> {
                    replaceFragment(AkunFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            // addToBackStack tidak dipakai, supaya tombol back langsung keluar aplikasi
            .commit()
    }
}