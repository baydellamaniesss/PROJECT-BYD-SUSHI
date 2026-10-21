package com.example.byd_sushi.home.pertemuan_5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.byd_sushi.R
import com.example.byd_sushi.databinding.ActivityFifthBinding
import com.example.byd_sushi.databinding.ItemPenjualanBinding

data class Penjualan(
    val produk: String,
    val info: String,
    val harga: String,
    val status: String
)

class FifthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthBinding

    private val dataPenjualan = listOf(
        Penjualan("Lip Cream Velvet", "30 Sep 2026 · 4 pcs", "Rp360.000", "Selesai"),
        Penjualan("Serum Vitamin C", "30 Sep 2026 · 2 pcs", "Rp200.000", "Selesai"),
        Penjualan("Bedak Tabur Matte", "29 Sep 2026 · 6 pcs", "Rp420.000", "Selesai"),
        Penjualan("Sunscreen SPF 50", "29 Sep 2026 · 3 pcs", "Rp255.000", "Diproses"),
        Penjualan("Kuas Blush", "28 Sep 2026 · 5 pcs", "Rp275.000", "Selesai")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Data Penjualan"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // isi daftar transaksi dari list data
        dataPenjualan.forEach { item ->
            val row = ItemPenjualanBinding.inflate(layoutInflater, binding.listPenjualan, false)
            row.tvProduk.text = item.produk
            row.tvInfo.text = item.info
            row.tvHarga.text = item.harga
            row.tvStatus.text = item.status
            val warna = if (item.status == "Selesai") R.color.teal_accent else R.color.gold
            row.tvStatus.setTextColor(ContextCompat.getColor(this, warna))
            binding.listPenjualan.addView(row.root)
        }

        binding.btnWebView.setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        binding.fabTambah.setOnClickListener {
            startActivity(Intent(this, InputPenjualanActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_search -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}