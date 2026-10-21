package com.example.byd_sushi

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.byd_sushi.databinding.ActivityDashboardBinding
import com.example.byd_sushi.pertemuan_5.FifthActivity
import com.example.byd_sushi.pertemuan_5.WebViewActivity
import com.example.byd_sushi.pertemuan_6.ProfilDeveloperActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Dashboard Hebepku"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // sapaan memakai username yang disimpan saat login
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Pengguna") ?: "Pengguna"
        binding.tvUsername.text = username.replaceFirstChar { it.uppercase() }

        binding.btnDataPenjualan.setOnClickListener {
            startActivity(Intent(this, FifthActivity::class.java))
        }

        binding.btnInfoWeb.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        binding.btnProfil.setOnClickListener {
            startActivity(Intent(this, ProfilDeveloperActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            true
        } else super.onOptionsItemSelected(item)
    }
}